package com.epam.monobankgptdemo;

public class DevRelza {
    private DevRelAnalyzer analyzer          = ObjectFactory.getInstance().createObject(DevRelAnalyzer.class);
    private MetricsCollector metricCollector = ObjectFactory.getInstance().createObject(MetricsCollector.class);
    private DevRelActionProducer producer    = ObjectFactory.getInstance().createObject(DevRelActionProducer.class);

    public void executeDevRelStrategy() {

        String devRelActivity = analyzer.findMostCriticalActivity();

        double howMuch = metricCollector.collect(devRelActivity);

        producer.produce(devRelActivity);

        System.out.println("improved by: " + (metricCollector.collect(devRelActivity) - howMuch) * 100 +
                " DevDollars");
    }
}
