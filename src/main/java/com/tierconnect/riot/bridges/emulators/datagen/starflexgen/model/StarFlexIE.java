package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.common.base.Preconditions;

import com.tierconnect.riot.bridges.emulators.utils.JsonUtils;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jantezana on 3/21/16.
 */
public class StarFlexIE implements StarFlex {
    private String macId;
    private InterestingEvents interestingEvents;

    private StarFlexIE(final StarFlexIEBuilder builder) {
        this.macId = builder.getMacId();
        this.setInterestingEvents(builder.getInterestingEvents());
    }

    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public String getTopic() {
        return String.format("%s/%s/interesting_events", this.getHeader(), this.getMacId());
    }

    @Override
    public String getMessage() {
        return String.format("%s", JsonUtils.convertObjectToJson(this.getInterestingEvents()));
    }

    public String getMacId() {
        return macId;
    }

    public InterestingEvents getInterestingEvents() {
        return interestingEvents;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public void setInterestingEvents(InterestingEvents interestingEvents) {
        this.interestingEvents = interestingEvents;
    }

    /**
     * InterestingEvents class.
     */
    public static class InterestingEvents implements Serializable {
        @JsonProperty(value = "ts_ms")
        private long tsMs;

        @JsonProperty(value = "tz")
        private String tz;

        @JsonProperty(value = "wallclock")
        private String wallclock;

        @JsonProperty(value = "upTime_sec")
        private double upTimeSec;

        @JsonProperty(value = "loadAvg")
        private List<Double> loadAvg;

        @JsonProperty(value = "totMem")
        private long totMen;

        @JsonProperty(value = "freeMem")
        private long freeMem;

        @JsonProperty(value = "hostName")
        private String hostName;

        @JsonProperty(value = "temperature")
        private Temperature temperature;

        @JsonProperty(value = "activeRFIDProgramName")
        private String activeRFIDProgramName;

        @JsonProperty(value = "cpuUtilPercent")
        private double cpuUtilPercent;

        @JsonProperty(value = "metaData")
        private MetaData metaData;

        public InterestingEvents(InterestingEventsBuilder builder) {
            this.tsMs = builder.getTsMs();
            this.tz = builder.getTz();
            this.wallclock = builder.getWallclock();
            this.upTimeSec = builder.getUpTimeSec();
            this.loadAvg = builder.getLoadAvg();
            this.totMen = builder.getTotMen();
            this.freeMem = builder.getFreeMem();
            this.hostName = builder.getHostName();
            this.temperature = builder.getTemperature();
            this.activeRFIDProgramName = builder.getActiveRFIDProgramName();
            this.cpuUtilPercent = builder.getCpuUtilPercent();
            this.metaData = builder.getMetaData();
        }

        public static class InterestingEventsBuilder {
            public static final String DEFAULT_TZ;
            public static final String DEFAULT_WALLCLOCK;
            public static final double DEFAULT_UP_TIME_SEC;
            public static final List<Double> DEFAULT_LOAD_AVG;
            public static final long DEFAULT_TOT_MEM;
            public static final long DEFAULT_FREE_MEM;
            public static final String DEFAULT_HOST_NAME;
            public static final Temperature DEFAULT_TEMPERATURE;
            public static final String DEFAULT_ACTIVE_RFID_PROGRAM_NAME;
            public static final double DEFAULT_CPU_UTIL_PERCENT;
            public static final MetaData DEFAULT_METADATA;

            private long tsMs;
            private String tz;
            private String wallclock;
            private double upTimeSec;
            private List<Double> loadAvg;
            private long totMen;
            private long freeMem;
            private String hostName;
            private Temperature temperature;
            private String activeRFIDProgramName;
            private double cpuUtilPercent;
            private MetaData metaData;

            static {
                DEFAULT_TZ = "UTC";
                DEFAULT_WALLCLOCK = "Fri Mar 18 2016 18:53:53 GMT+0000 (UTC)";
                DEFAULT_UP_TIME_SEC = 25114.756431039;
                DEFAULT_LOAD_AVG = new LinkedList<Double>();
                DEFAULT_LOAD_AVG.add(0.0029296875);
                DEFAULT_LOAD_AVG.add(0.0146484375);
                DEFAULT_LOAD_AVG.add(0.04541015625);
                DEFAULT_TOT_MEM = 252280832;
                DEFAULT_FREE_MEM = 185925632;
                DEFAULT_HOST_NAME = "mojixe729bb";
                DEFAULT_TEMPERATURE = new Temperature(43, 109.4);
                DEFAULT_ACTIVE_RFID_PROGRAM_NAME = "basic";
                DEFAULT_CPU_UTIL_PERCENT = 7.801560312062413;
                DEFAULT_METADATA = new MetaData("keepAlive", "BT0001");
            }

            public long getTsMs() {
                return tsMs;
            }

            public String getTz() {
                return tz;
            }

            public String getWallclock() {
                return wallclock;
            }

            public double getUpTimeSec() {
                return upTimeSec;
            }

            public List<Double> getLoadAvg() {
                return loadAvg;
            }

            public long getTotMen() {
                return totMen;
            }

            public long getFreeMem() {
                return freeMem;
            }

            public String getHostName() {
                return hostName;
            }

            public Temperature getTemperature() {
                return temperature;
            }

            public String getActiveRFIDProgramName() {
                return activeRFIDProgramName;
            }

            public double getCpuUtilPercent() {
                return cpuUtilPercent;
            }

            public MetaData getMetaData() {
                return metaData;
            }

            public InterestingEventsBuilder setTsMs(final long tsMs) {
                this.tsMs = tsMs;
                return this;
            }

            public InterestingEventsBuilder setTz(final String tz) {
                this.tz = tz;
                return this;
            }

            public InterestingEventsBuilder setWallclock(final String wallclock) {
                this.wallclock = wallclock;
                return this;
            }

            public InterestingEventsBuilder setUpTimeSec(final double upTimeSec) {
                this.upTimeSec = upTimeSec;
                return this;
            }

            public InterestingEventsBuilder setLoadAvg(final List<Double> loadAvg) {
                this.loadAvg = loadAvg;
                return this;
            }

            public InterestingEventsBuilder setTotMen(final long totMen) {
                this.totMen = totMen;
                return this;
            }

            public InterestingEventsBuilder setFreeMem(final long freeMem) {
                this.freeMem = freeMem;
                return this;
            }

            public InterestingEventsBuilder setHostName(final String hostName) {
                this.hostName = hostName;
                return this;
            }

            public InterestingEventsBuilder setTemperature(final Temperature temperature) {
                this.temperature = temperature;
                return this;
            }

            public InterestingEventsBuilder setActiveRFIDProgramName(final String activeRFIDProgramName) {
                this.activeRFIDProgramName = activeRFIDProgramName;
                return this;
            }

            public InterestingEventsBuilder setCpuUtilPercent(final double cpuUtilPercent) {
                this.cpuUtilPercent = cpuUtilPercent;
                return this;
            }

            public InterestingEventsBuilder setMetaData(final MetaData metaData) {
                this.metaData = metaData;
                return this;
            }

            public InterestingEventsBuilder setDefaultValues() {
                this.tsMs = new Date().getTime();
                this.tz = DEFAULT_TZ;
                this.wallclock = DEFAULT_WALLCLOCK;
                this.upTimeSec = DEFAULT_UP_TIME_SEC;
                this.loadAvg = DEFAULT_LOAD_AVG;
                this.totMen = DEFAULT_TOT_MEM;
                this.freeMem = DEFAULT_FREE_MEM;
                this.hostName = DEFAULT_HOST_NAME;
                this.temperature = DEFAULT_TEMPERATURE;
                this.activeRFIDProgramName = DEFAULT_ACTIVE_RFID_PROGRAM_NAME;
                this.cpuUtilPercent = DEFAULT_CPU_UTIL_PERCENT;
                this.metaData = DEFAULT_METADATA;
                return this;
            }

            public InterestingEventsBuilder setRandomValues(final String macId) {
                Preconditions.checkNotNull(macId);
                this.tsMs = Calendar.getInstance().getTimeInMillis();
                this.tz = DEFAULT_TZ;
                this.wallclock = DEFAULT_WALLCLOCK;
                this.upTimeSec = DEFAULT_UP_TIME_SEC;
                this.loadAvg = DEFAULT_LOAD_AVG;
                this.totMen = DEFAULT_TOT_MEM;
                this.freeMem = DEFAULT_FREE_MEM;
                this.hostName = DEFAULT_HOST_NAME;
                this.temperature = DEFAULT_TEMPERATURE;
                this.activeRFIDProgramName = DEFAULT_ACTIVE_RFID_PROGRAM_NAME;
                this.cpuUtilPercent = DEFAULT_CPU_UTIL_PERCENT;
                this.metaData = DEFAULT_METADATA;
                this.metaData.setMyId(macId);
                return this;
            }

            public InterestingEvents build() {
                return new InterestingEvents(this);
            }
        }

        /**
         * Temperature class.
         */
        public static class Temperature implements Serializable {

            @JsonProperty(value = "C")
            private double celsius;

            @JsonProperty(value = "F")
            private double fahrenheit;

            public Temperature(final double celsius, final double fahrenheit) {
                this.celsius = celsius;
                this.fahrenheit = fahrenheit;
            }

            public double getCelsius() {
                return celsius;
            }

            public void setCelsius(double celsius) {
                this.celsius = celsius;
            }

            public double getFahrenheit() {
                return fahrenheit;
            }

            public void setFahrenheit(double fahrenheit) {
                this.fahrenheit = fahrenheit;
            }
        }

        /**
         * MetaData class.
         */
        public static class MetaData implements Serializable {
            private String type;

            @JsonProperty(value = "myID")
            private String myId;

            public MetaData(final String type, final String myId) {
                Preconditions.checkNotNull(type);
                Preconditions.checkNotNull(myId);
                this.type = type;
                this.myId = myId;
            }

            public String getType() {
                return type;
            }

            public void setType(final String type) {
                Preconditions.checkNotNull(type);
                this.type = type;
            }

            public String getMyId() {
                return myId;
            }

            public void setMyId(final String myId) {
                Preconditions.checkNotNull(myId);
                this.myId = myId;
            }
        }
    }

    public static class StarFlexIEBuilder {
        private String header;
        private String macId;
        private StarFlexIE.InterestingEvents interestingEvents;

        public String getHeader() {
            return header;
        }

        public String getMacId() {
            return macId;
        }

        public InterestingEvents getInterestingEvents() {
            return interestingEvents;
        }

        public StarFlexIEBuilder setMacId(final String macId) {
            Preconditions.checkNotNull(macId);
            this.macId = macId;
            return this;
        }

        public StarFlexIEBuilder setInterestingEvents(InterestingEvents interestingEvents) {
            Preconditions.checkNotNull(interestingEvents);
            this.interestingEvents = interestingEvents;
            return this;
        }

        public StarFlexIEBuilder setDefaultValues() {
            this.macId = DEFAULT_MAC_ID;
            this.interestingEvents = new InterestingEvents.InterestingEventsBuilder().setDefaultValues().build();
            return this;
        }

        public StarFlexIEBuilder setRandomValues() {
            // Build Interesting events;
            InterestingEvents.InterestingEventsBuilder builder = new InterestingEvents.InterestingEventsBuilder();
            builder.setRandomValues(this.macId);
            // Fri Mar 18 2016 18:53:53 GMT+0000 (UTC)
            //builder.setWallclock(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("E MMM d yyyy HH:mm:ss O")));

            this.interestingEvents = builder.build();
            return this;
        }

        public StarFlexIE build() {
            return new StarFlexIE(this);
        }
    }
}
