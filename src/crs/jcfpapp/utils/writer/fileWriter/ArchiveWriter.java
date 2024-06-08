package crs.jcfpapp.utils.writer.fileWriter;

import crs.jcfpapp.utils.writer.MyWriter;

import java.util.ArrayList;

public class ArchiveWriter implements MyWriter<ArrayList<String>> {
    @Override
    public boolean write(String way, ArrayList<String> data, boolean f) {
        return false;
    }
}
