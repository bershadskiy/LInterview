package entity;

import java.util.List;

public interface IReporter {
    void addReport(String report);
    boolean hasNoReports();
    List<String> getReports();
}
