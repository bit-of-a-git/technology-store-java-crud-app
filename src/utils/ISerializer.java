package utils;

// TODO Nothing!  This class is complete but you need to implement this in API classes
public interface ISerializer {
    void save() throws Exception;

    void load() throws Exception;

    String fileName();
}
