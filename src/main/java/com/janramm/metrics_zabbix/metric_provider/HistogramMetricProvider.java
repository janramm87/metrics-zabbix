package com.janramm.metrics_zabbix.metric_provider;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Snapshot;
import com.janramm.metrics_zabbix.zabbix_key_enums.SnapshotValueKeys;
import com.quigley.zabbixj.metrics.MetricsException;
import com.quigley.zabbixj.metrics.MetricsKey;
import com.quigley.zabbixj.metrics.MetricsProvider;


public class HistogramMetricProvider implements MetricsProvider {
  private final Histogram histogram;

  public HistogramMetricProvider(Histogram histogram) {
    this.histogram = histogram;
  }

  @Override
  public Object getValue(MetricsKey mKey) throws MetricsException {
    SnapshotValueKeys histogramValueKey = SnapshotValueKeys.valueOf(mKey.getKey());
    Snapshot snapshot = histogram.getSnapshot();
    switch (histogramValueKey) {
      case MEAN:
        return snapshot.getMean();
      case MAX:
        return snapshot.getMax();
      case MIN:
        return snapshot.getMin();
      case MEDIAN:
        return snapshot.getMedian();
      case P75TH:
        return snapshot.get75thPercentile();
      case P95TH:
        return snapshot.get95thPercentile();
      case P98TH:
        return snapshot.get98thPercentile();
      case P99TH:
        return snapshot.get99thPercentile();
      case P999TH:
        return snapshot.get999thPercentile();
      case STDDEV:
        return snapshot.getStdDev();
      case COUNT:
        return histogram.getCount();
    }
    throw new MetricsException("Key " + mKey.getKey() + " not found");
  }
}
