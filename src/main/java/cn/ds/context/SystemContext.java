package cn.ds.context;

public class SystemContext {

    private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();

    public static int getOffset(){
        Integer o = offset.get();
        if(o==null||o<1){
            return 1;
        }
        return o;
    }

    public static void setOffset(int o){
        offset.set(o);
    }

    public static int getPageSize(){
        Integer o = pageSize.get();
        if(o==null||o<1){
            return 10;
        }
        return o;
    }

    public static void setPageSize(int o){
        pageSize.set(o);
    }

    public static void deleteOffset(){
        offset.remove();
    }

    public static void deletePageSize(){
         pageSize.remove();
    }
}
