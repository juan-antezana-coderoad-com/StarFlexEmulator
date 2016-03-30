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

    /**
     * Builds an instance of StarFlexIE
     *
     * @param builder the StarFlexIEBuilder
     */
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

    /**
     * Gets the value of macId
     *
     * @return the value of macId
     */
    public String getMacId() {
        return macId;
    }

    /**
     * Gets the InterestingEvents
     *
     * @return the InterestingEvents
     */
    public InterestingEvents getInterestingEvents() {
        return interestingEvents;
    }

    /**
     * Sets the value of macId
     *
     * @param macId the new value of macId
     */
    public void setMacId(String macId) {
        this.macId = macId;
    }

    /**
     * Sets the interesting_events
     *
     * @param interestingEvents the new interesting_events
     */
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

        /**
         * Builds an instance of InterestingEvents
         *
         * @param builder the InterestingEventsBuilder
         */
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

        /**
         * InterestingEventsBuilder class.
         */
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

            /**
             * Initialize the default values.
             *
             */
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

            /**
             * Gets the value of tsMs
             *
             * @return the value of tsMs
             */
            public long getTsMs() {
                return tsMs;
            }

            /**
             * Gets the value of tz
             *
             * @return the value of tz
             */
            public String getTz() {
                return tz;
            }

            /**
             * Gets the value of wallclock
             *
             * @return the value of wallclock
             */
            public String getWallclock() {
                return wallclock;
            }

            /**
             * Gets the value of upTimeSec
             *
             * @return the value of upTimeSec
             */
            public double getUpTimeSec() {
                return upTimeSec;
            }

            /**
             * Gets the list of loadAvg
             *
             * @return the list of loadAvg
             */
            public List<Double> getLoadAvg() {
                return loadAvg;
            }

            /**
             * Gets the value of totMem
             *
             * @return the value of totMem
             */
            public long getTotMen() {
                return totMen;
            }

            /**
             * Gets the value of freeMem
             *
             * @return the value of freeMem
             */
            public long getFreeMem() {
                return freeMem;
            }

            /**
             * Gets the hostName
             *
             * @return the hostName
             */
            public String getHostName() {
                return hostName;
            }

            /**
             * Gets the temperature
             *
             * @return the temperature
             */
            public Temperature getTemperature() {
                return temperature;
            }

            /**
             * Gets the activeRFIDProgramName
             *
             * @return the activeRFIDProgramName
             */
            public String getActiveRFIDProgramName() {
                return activeRFIDProgramName;
            }

            /**
             * Gets the cpuUtilPercent
             *
             * @return the cpuUtilPercent
             */
            public double getCpuUtilPercent() {
                return cpuUtilPercent;
            }

            /**
             * Gets the metaData
             *
             * @return the metaData
             */
            public MetaData getMetaData() {
                return metaData;
            }

            /**
             * Sets the tsMs
             *
             * @param tsMs the new tsMs
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setTsMs(final long tsMs) {
                this.tsMs = tsMs;
                return this;
            }

            /**
             * Sets the tz.
             *
             * @param tz the new tz
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setTz(final String tz) {
                this.tz = tz;
                return this;
            }

            /**
             * Sets the wallclock
             *
             * @param wallclock the wallclock
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setWallclock(final String wallclock) {
                this.wallclock = wallclock;
                return this;
            }

            /**
             * Sets the getUpTimeSec
             *
             * @param upTimeSec the new upTimeSec
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setUpTimeSec(final double upTimeSec) {
                this.upTimeSec = upTimeSec;
                return this;
            }

            /**
             * Sets the list of loadAvg
             *
             * @param loadAvg the new list of loadAvg
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setLoadAvg(final List<Double> loadAvg) {
                this.loadAvg = loadAvg;
                return this;
            }

            /**
             * Sets the totMem
             *
             * @param totMen the totMem
             * @return the new InterestingEventsBuilder
             */
            public InterestingEventsBuilder setTotMen(final long totMen) {
                this.totMen = totMen;
                return this;
            }

            /**
             * Sets the freeMem
             *
             * @param freeMem the new freeMem
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setFreeMem(final long freeMem) {
                this.freeMem = freeMem;
                return this;
            }

            /**
             * Sets the hostName
             *
             * @param hostName the new hostName
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setHostName(final String hostName) {
                this.hostName = hostName;
                return this;
            }

            /**
             * Sets the temperature
             *
             * @param temperature the new temperature
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setTemperature(final Temperature temperature) {
                this.temperature = temperature;
                return this;
            }

            /**
             * Sets the activeRFIDProgramName
             *
             * @param activeRFIDProgramName the new activeRFIDProgramName
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setActiveRFIDProgramName(final String activeRFIDProgramName) {
                this.activeRFIDProgramName = activeRFIDProgramName;
                return this;
            }

            /**
             * Sets the cpuUtilPercent
             *
             * @param cpuUtilPercent the new cpuUtilPercent
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setCpuUtilPercent(final double cpuUtilPercent) {
                this.cpuUtilPercent = cpuUtilPercent;
                return this;
            }

            /**
             * Sets the metaData
             *
             * @param metaData the new metaData
             * @return the InterestingEventsBuilder
             */
            public InterestingEventsBuilder setMetaData(final MetaData metaData) {
                this.metaData = metaData;
                return this;
            }

            /**
             * Sets all values with default values.
             *
             * @return the InterestingEventsBuilder
             */
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

            /**
             * Sets all values with random values.
             *
             * @param macId the value of macId
             * @return the InterestingEventsBuilder
             */
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

            /**
             * Builds an InterestingEvents
             *
             * @return the InterestingEvents
             */
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

            /**
             * Builds an instance of Temperature
             *
             * @param celsius
             * @param fahrenheit
             */
            public Temperature(final double celsius, final double fahrenheit) {
                this.celsius = celsius;
                this.fahrenheit = fahrenheit;
            }

            /**
             * Gets the value of celsius
             *
             * @return the value of celsius
             */
            public double getCelsius() {
                return celsius;
            }

            /**
             * Gets the value of fahrenheit
             *
             * @return the value of fahrenheit
             */
            public double getFahrenheit() {
                return fahrenheit;
            }

            /**
             * Sets the value of celsius
             *
             * @param celsius the new values of celsius
             */
            public void setCelsius(final double celsius) {
                this.celsius = celsius;
            }


            /**
             * Sets the value of fahrenheit
             *
             * @param fahrenheit the new value of fahrenheit
             */
            public void setFahrenheit(final double fahrenheit) {
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

            /**
             * Builds an instance of MetaData
             *
             * @param type the value of type
             * @param myId the value of myID
             */
            public MetaData(final String type, final String myId) {
                Preconditions.checkNotNull(type);
                Preconditions.checkNotNull(myId);
                this.type = type;
                this.myId = myId;
            }

            /**
             * Gets the value of type
             *
             * @return the value of type
             */
            public String getType() {
                return type;
            }

            /**
             * Gets the value of myID
             *
             * @return the value of myID
             */
            public String getMyId() {
                return myId;
            }

            /**
             * Sets the value of type
             *
             * @param type the new value of type
             */
            public void setType(final String type) {
                Preconditions.checkNotNull(type);
                this.type = type;
            }

            /**
             * Sets the value of myID
             *
             * @param myId the new value of myID
             */
            public void setMyId(final String myId) {
                Preconditions.checkNotNull(myId);
                this.myId = myId;
            }
        }
    }

    /**
     * StarFlexIEBuilder class.
     */
    public static class StarFlexIEBuilder {
        private String header;
        private String macId;
        private StarFlexIE.InterestingEvents interestingEvents;

        /**
         * Gets the value of header
         *
         * @return the value of header
         */
        public String getHeader() {
            return header;
        }

        /**
         * Gets the value of macId
         *
         * @return the value of macId
         */
        public String getMacId() {
            return macId;
        }

        /**
         * Gets the interestingEvents
         *
         * @return the interestingEvents
         */
        public InterestingEvents getInterestingEvents() {
            return interestingEvents;
        }

        /**
         * Sets the value of macId
         *
         * @param macId the new macId
         * @return the StarFlexIEBuilder
         */
        public StarFlexIEBuilder setMacId(final String macId) {
            Preconditions.checkNotNull(macId);
            this.macId = macId;
            return this;
        }

        /**
         * Sets the interestingEvents
         *
         * @param interestingEvents the new interestingEvents
         * @return the StarFlexIEBuilder
         */
        public StarFlexIEBuilder setInterestingEvents(InterestingEvents interestingEvents) {
            Preconditions.checkNotNull(interestingEvents);
            this.interestingEvents = interestingEvents;
            return this;
        }

        /**
         * Sets all values with default values.
         *
         * @return the StarFlexIEBuilder
         */
        public StarFlexIEBuilder setDefaultValues() {
            this.macId = DEFAULT_MAC_ID;
            this.interestingEvents = new InterestingEvents.InterestingEventsBuilder().setDefaultValues().build();
            return this;
        }

        /**
         * Sets all values with random values.
         *
         * @return the StarFlexIEBuilder
         */
        public StarFlexIEBuilder setRandomValues() {
            // Build Interesting events;
            InterestingEvents.InterestingEventsBuilder builder = new InterestingEvents.InterestingEventsBuilder();
            builder.setRandomValues(this.macId);
            // Fri Mar 18 2016 18:53:53 GMT+0000 (UTC)
            //builder.setWallclock(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("E MMM d yyyy HH:mm:ss O")));

            this.interestingEvents = builder.build();
            return this;
        }

        /**
         * Builds a StarFlexIE
         *
         * @return the StarFlexIE
         */
        public StarFlexIE build() {
            return new StarFlexIE(this);
        }
    }
}
