package crs.jcfpapp.utils.writer;

public interface MyWriter<T> {
    boolean write(String way, T data, boolean f);
}
