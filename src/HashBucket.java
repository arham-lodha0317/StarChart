import java.util.LinkedList;

public class HashBucket {

    private LinkedList<Entry>[] hashtable;
    private int capacity;
    private int probes = 0;

    HashBucket(){
        capacity = 101;
        hashtable = new LinkedList[capacity];
    }

    HashBucket(int initcap){
        capacity = initcap;
        hashtable = new LinkedList[capacity];
    }

    Object put(Object key, Object value){

        int hc= key.hashCode();
        int index = hc % capacity;
        //System.out.println("entering");
        if(hashtable[index] == null){
            hashtable[index] = new LinkedList<Entry>();
            hashtable[index].add(new Entry(key, value));
        }
        else{
            for (int i = 0; i < hashtable[index].size(); i++) {

                if((hashtable[index].get(i)).getKey().equals(key)){

                    Entry object = (hashtable[index].get(i));

                    hashtable[index].add(new Entry(key, value));

                    return object.getValue();
                }

            }

            hashtable[index].add(new Entry(key, value));
            return null;
        }

        return null;
    }

    Object get(Object key){

        int hc = key.hashCode();
        int index = hc % capacity;

        if(hashtable[index] == null){
            return null;
        }

        for (int i = 0; i < hashtable[index].size(); i++) {

            if((hashtable[index].get(i)).getKey().equals(key)){
                return hashtable[index].get(i).getValue();
            }

        }

        return null;
    }

    Object remove(Object key){
        int hc = key.hashCode();
        int index = hc % capacity;

        if(hashtable[index] == null){
            return null;
        }

        for (int i = 0; i < hashtable[index].size() ; i++) {

            if((hashtable[index].get(i)).getKey().equals(key)){
                Object object = hashtable[index].get(i).getValue();
                hashtable[index].remove(i);
                return object;
            }

        }

        return null;
    }

    double averageBucketSize() {
        double average = 0.0;

        for (LinkedList l: hashtable) {

            if(l != null){
                average += l.size();
            }

        }

        return average /(double) capacity;
    }

    int maxBucketSize(){
        int max = 0;

        for (LinkedList l: hashtable) {
            if(l != null && l.size() > max){
                max = l.size();
            }
        }

        return max;
    }

    int minBuckSize(){
        int min = Integer.MAX_VALUE;

        for (LinkedList l: hashtable) {
            if(l != null && l.size() < min){
                min = l.size();
            }
        }

        return min;
    }

    private static class Entry{
        Object key;
        Object value;
        boolean removed;

        Entry(){
            key = null;
            value = null;
            removed = false;
        }

        Entry(Object key, Object value){
            this.key = key;
            this.value = value;
            removed = false;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }


        public String toString(){
            return key + "\t" + value;
        }

        public boolean isRemoved() {
            return removed;
        }

        public void setRemoved(boolean removed) {
            this.removed = removed;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
