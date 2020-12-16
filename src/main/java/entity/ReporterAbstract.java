package entity;

import java.util.LinkedList;
import java.util.List;

abstract class ReporterAbstract implements IReporter {
    protected LinkedList<String> reports;

    @Override
    public void addReport(String report) {
        //skip any actions if the argument is mean-less
        if (null == report || report.isEmpty())
            return;

        if (null == this.reports) {
            this.reports = new LinkedList<>();
        }
        this.reports.addLast(report);
    }

    @Override
    public boolean hasNoReports() {
        return null == this.reports || this.reports.isEmpty();
    }

    @Override
    public LinkedList<String> getReports() {
        return this.reports;
    }
}
