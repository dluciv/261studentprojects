namespace DB3.Interfaces
{
    interface IndxData<TKey,TData>: DBData
    {
        TKey ExtractKey();
        TData ExtractData();
    }
}