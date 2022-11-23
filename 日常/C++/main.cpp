#include <iostream>
#include <cstring>
#include <cstdlib>
#include <random>
#include <unordered_map>
#include <algorithm>
#include <ctime>
#include "MurmurHash3.h"
using namespace std;

#define KEY_SIZE 13
#define HASH_SEED 0
typedef int32_t count_type;

static mt19937 rng(time(0));
static const int COUNT[2] = { 1, -1 };

bool cmpPairFunc(pair<uint8_t*, int>p1, pair<uint8_t*, int>p2)
{
    return p1.second > p2.second;
}

//for recording information in the unordered_map
struct CmpFunc {
    bool operator()(const uint8_t* keyA, const uint8_t* keyB) const {
        return memcmp(keyA, keyB, KEY_SIZE) == 0;
    }
};
struct HashFunc {
    unsigned int operator()(const uint8_t* key) const {
        unsigned int hashValue=0;
        MurmurHash3_x86_32(key, KEY_SIZE,0, &hashValue);;
        return hashValue;
    }
};

template <uint32_t BUCKET_NUM,uint32_t CELL_NUM>
class WavingSketch {
private:
    struct node {count_type C=0; uint8_t key[KEY_SIZE]{0};} bucketArray[BUCKET_NUM][CELL_NUM];
    count_type incast;
public:
    void insert(uint8_t* key){
        uint64_t hashValue[2]{0};
        MurmurHash3_x64_128(key, KEY_SIZE, HASH_SEED, &hashValue);
        unsigned int bktIdx=hashValue[1]%BUCKET_NUM;
        uint32_t choice = hashValue[0] & 1;
        uint32_t min_num = UINT32_MAX;
        uint32_t min_pos = -1;


        for (uint32_t i = 0; i < CELL_NUM; ++i) {
            if (bucketArray[bktIdx][i].C == 0) {
                // The error free item's counter is negative, which is a trick to
                // be differentiated from items which are not error free.
                memcpy(bucketArray[bktIdx][i].key, key, KEY_SIZE);
                bucketArray[bktIdx][i].C = -1;
                return;
            }
            else if (memcmp(key,bucketArray[bktIdx][i].key,KEY_SIZE)==0) {
                if (bucketArray[bktIdx][i].C < 0)
                    bucketArray[bktIdx][i].C--;
                else {
                    bucketArray[bktIdx][i].C++;
                    incast += COUNT[choice];
                }
                return;
            }

            count_type counter_val = std::abs(bucketArray[bktIdx][i].C);
            if (counter_val < min_num) {
                min_num = counter_val;
                min_pos = i;
            }
        }

        if (incast * COUNT[choice] >= min_num) {
            if (bucketArray[bktIdx][min_pos].C < 0) {

                uint64_t hashValue[2]{0};
                MurmurHash3_x64_128(key, KEY_SIZE, HASH_SEED, &hashValue);
                uint32_t min_choice=hashValue[0]%1;
                incast -= COUNT[min_choice] * bucketArray[bktIdx][min_pos].C;
            }
            memcpy(bucketArray[bktIdx][min_pos].key,key,KEY_SIZE);
            bucketArray[bktIdx][min_pos].C=min_num + 1;
        }
        incast += COUNT[choice];
    }


    void getEstimatedFlowSizes(unordered_map<uint8_t*, unsigned int, HashFunc, CmpFunc>& estimatedFlowSizes) {
        for (uint32_t bktIdx = 0; bktIdx < BUCKET_NUM; bktIdx++) {
            for (int i=0; i<CELL_NUM; i++)
            {
                uint8_t* key = (uint8_t*)malloc(KEY_SIZE);
                memcpy(key,bucketArray[bktIdx][i].key,KEY_SIZE);
                estimatedFlowSizes[key]=std::abs(bucketArray[bktIdx][i].C);;
            }
        }
    }
};


unsigned int ReadInTraces(const char* tracePreFix, uint8_t** keys, unordered_map<uint8_t*, unsigned int, HashFunc, CmpFunc>& actualFlowSizes, unsigned int maxItemNum)
{
    unsigned int count = 0;

    unsigned int countInFile = 0;
    for (int datafileCnt = 0; datafileCnt <= 10; ++datafileCnt){
        countInFile = 0;
        char traceFilePath[100];
        sprintf(traceFilePath, "%s%d.dat", tracePreFix, datafileCnt);
        printf("Start reading %s\n", traceFilePath);

        FILE* fin = fopen(traceFilePath, "rb");
        char temp[KEY_SIZE]{ 0 };
        uint8_t* key;
        while (fread(temp, 1, KEY_SIZE, fin) == KEY_SIZE) {
            key = (uint8_t*)malloc(KEY_SIZE);
            memcpy(key, temp, KEY_SIZE);
            keys[count] = key;
            if (actualFlowSizes.find(key) == actualFlowSizes.end()) {
                actualFlowSizes[key] = 1;
            }
            else {
                actualFlowSizes[key] += 1;
            }
            count++;
            if(count>=maxItemNum){
                printf("The dataset has more than %d items, set a larger value for maxItemNum", maxItemNum);
                exit(-1);
            }

            countInFile++;
            if (countInFile % 5000000 == 0) {
                printf("\thave read %u items in %s, the dataset now has %u items\n", countInFile,traceFilePath, count);
            }
        }
        fclose(fin);
        printf("Finish reading %s (%u items), the dataset now has %u items\n", traceFilePath,countInFile,count);

    }
	return count;
}



int main()
{
    //prepare dataset
    cout << "prepare dataset" << endl;
    unsigned int maxItemNum = 40*1000000;//max number of items
    uint8_t** keys = (uint8_t**)calloc(maxItemNum, sizeof(uint8_t*));//to store keys of all items
    unordered_map<uint8_t*, unsigned int, HashFunc, CmpFunc> actualFlowSizes;//count the actual flow sizes
    unsigned int actualItemNum = ReadInTraces(R"(../../data/)", keys, actualFlowSizes,maxItemNum);
    cout << "number of items: " << actualItemNum << endl;
    cout << "number of flows: " << actualFlowSizes.size() << endl;
    cout << "*********************" << endl;

    //prepare algorithm
    cout << "prepare algorithm" << endl;
    //parameters
    //200KB
    const uint32_t cellNum=16;
    const uint32_t bucketNum=825;
    unsigned int tempK=1000;

    WavingSketch<bucketNum,cellNum> ws;

    //output memory info
    double bucketMem = bucketNum * (cellNum * (KEY_SIZE+19/8.0)+19/8.0) /1024.0;
    cout << "bucketMem: " << bucketMem << "KB" << endl;
    cout << "*********************" << endl;

    //insert items
    cout << "insert items" << endl;
    clock_t time1 = clock();
    for (unsigned int i = 0; i < actualItemNum; i++) {
        ws.insert(keys[i]);
    }
    clock_t time2 = clock();

    //calculate throughput
    double numOfSeconds = (double)(time2 - time1) / CLOCKS_PER_SEC;//the seconds using to insert items
    double throughput = (actualItemNum / 1000000.0) / numOfSeconds;
    cout << "use " << numOfSeconds << " seconds" << endl;
    cout << "throughput: " << throughput << " Mpps" << ", each insert operation uses " << 1000.0 / throughput << " ns" << endl;
    cout << "*********************" << endl;

    //calculate precision, ARE, AAE
    //get sorted acutal flow sizes and sorted estimated flow sizes
    vector<pair<uint8_t*, unsigned int>> actualFlowSizesVector;
    for (auto iter = actualFlowSizes.begin(); iter != actualFlowSizes.end(); iter++) {
        actualFlowSizesVector.push_back(make_pair(iter->first, iter->second));
    }
    sort(actualFlowSizesVector.begin(), actualFlowSizesVector.end(), cmpPairFunc);

    vector<pair<uint8_t*, unsigned int>> estimatedFlowSizesVector;
    unordered_map<uint8_t*, unsigned int, HashFunc, CmpFunc> estimatedFlowSizes;
    ws.getEstimatedFlowSizes(estimatedFlowSizes);

    for (auto iter = estimatedFlowSizes.begin(); iter != estimatedFlowSizes.end(); iter++) {
        estimatedFlowSizesVector.push_back(make_pair(iter->first, iter->second));
    }

    cout << "actually recovered " << estimatedFlowSizes.size() << " flows" << endl;
    sort(estimatedFlowSizesVector.begin(), estimatedFlowSizesVector.end(), cmpPairFunc);

    unsigned int k = min((unsigned int)tempK, (unsigned int)estimatedFlowSizes.size());
    //get acutal top-k flows
    unordered_map<uint8_t*, unsigned int, HashFunc, CmpFunc> actualTopKFlowSizes;
    for (unsigned int i = 0; i < k; i++) {
        unsigned int actualSize = actualFlowSizesVector[i].second;
        actualTopKFlowSizes[actualFlowSizesVector[i].first] = actualSize;
    }

    cout << "Top-" << k << " flow detection Metrics" << endl;
    k = min((unsigned int)estimatedFlowSizes.size(), k);

    //top-k precision
    unsigned int TPNumOfTopK = 0;//True positive num
    for (int i = 0; i < k; i++) {
        uint8_t* key = estimatedFlowSizesVector[i].first;
        if (actualTopKFlowSizes.find(key) != actualTopKFlowSizes.end()) {
            TPNumOfTopK++;
        }
    }
    cout << "Precision: " << (double)TPNumOfTopK / k << endl;

    //ARE,AAE of the reported top-k flows
    double totalREOfTopK = 0;//total relative error of top-k flows
    double totalAEOfTopK = 0;
    for (int i = 0; i < k; i++) {
        uint8_t* key = estimatedFlowSizesVector[i].first;
        unsigned int estimatedSize = estimatedFlowSizesVector[i].second;
        unsigned int actualSize = 0;
        if (actualFlowSizes.find(key) != actualFlowSizes.end()) {
            actualSize = actualFlowSizes[key];
        }
        totalAEOfTopK += abs((double)estimatedSize - actualSize);
        double rE = abs((double)estimatedSize - actualSize) / actualSize;
        totalREOfTopK += rE;
    }
    cout << "ARE of reported top-k flows: " << totalREOfTopK / k << endl;
    cout << "AAE of reported top-k flows: " << totalAEOfTopK / k << endl;

    cout<<endl<<endl<<"------------------------------------------------"<<endl;

    //release resources
    for (auto iter = estimatedFlowSizes.begin(); iter != estimatedFlowSizes.end(); iter++) {
        free(iter->first);
    }
    for(unsigned int i=0;i<actualItemNum;i++){
        free(keys[i]);
    }
    free(keys);
    return 0;
}