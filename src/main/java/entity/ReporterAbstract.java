package entity;

import java.util.LinkedList;

abstract class ReporterAbstract extends Person implements IReporter {
    protected LinkedList<String> reports;

    public ReporterAbstract(int age, String address) {
        super(age, address);
    }

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
