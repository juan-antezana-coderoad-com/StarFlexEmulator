package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import com.google.common.base.Preconditions;
import com.tierconnect.riot.bridges.emulators.utils.JsonUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jantezana on 3/21/16.
 */
public class StarFlexData implements StarFlex {

    private String macId;

    private List<TagReadDataMessage> tagReadDataMessages;

    /**
     * Builds an instance of StarFlexData.
     *
     * @param builder the StarFlexDataBuilder
     */
    private StarFlexData(final StarFlexDataBuilder builder) {
        Preconditions.checkNotNull(builder);
        this.macId = builder.getMacId();
        this.tagReadDataMessages = builder.getTagReadDataMessages();
    }

    @Override
    public String getHeader() {
        return HEADER;
    }


    @Override
    public String getTopic() {
        return String.format("%s/%s/data", this.getHeader(), this.getMacId());
    }

    @Override
    public String getMessage() {
        return String.format("%s", JsonUtils.convertObjectToJson(this.tagReadDataMessages));
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
     * Gets the list of tagReadDataMessages
     *
     * @return the value of getTagReadDataMessage
     */
    public List<TagReadDataMessage> getTagReadDataMessages() {
        return tagReadDataMessages;
    }

    /**
     * TagReadDataMessage class.
     */
    public static class TagReadDataMessage implements Serializable {

        private String type;
        private long timestamp;
        private long seqNum;
        private String txAntennaPort;
        private String txExpanderPort;
        private String transmitSource;
        private String data;

        /**
         * Builds an instance of TagReadDataMessage
         *
         * @param builder the TagReadDataMessageBuilder
         */
        private TagReadDataMessage(final TagReadDataMessageBuilder builder) {
            type = builder.getType();
            timestamp = builder.getTimestamp();
            seqNum = builder.getSeqNum();
            txAntennaPort = builder.getTxAntennaPort();
            txExpanderPort = builder.getTxExpanderPort();
            transmitSource = builder.getTransmitSource();
            data = builder.getData();
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
         * Gets the value of timestamp
         *
         * @return the value of timestamp
         */
        public long getTimestamp() {
            return timestamp;
        }

        /**
         * Gets the value of seqNum
         *
         * @return the value of seqNum
         */
        public long getSeqNum() {
            return seqNum;
        }

        /**
         * Gets the value of txAntennaPort
         *
         * @return the value of txAntennaPort
         */
        public String getTxAntennaPort() {
            return txAntennaPort;
        }

        /**
         * Gets the value of txExpanderPort
         *
         * @return the value of txExpanderPort
         */
        public String getTxExpanderPort() {
            return txExpanderPort;
        }

        /**
         * Gets the value of transmitSource
         *
         * @return the value of transmitSource
         */
        public String getTransmitSource() {
            return transmitSource;
        }

        /**
         * Gets the data
         *
         * @return the data
         */
        public String getData() {
            return data;
        }

        /**
         * TagReadDataMessageBuilder class.
         */
        public static class TagReadDataMessageBuilder {
            private static final String DATA_FORMAT = "0x3000%s%05d62D3";
            public static final String DEFAULT_TYPE = "TagReadData";
            public static final long DEFAULT_TIMESTAMP = 1455135473992L;
            public static final int DEFAULT_SEQ_NUMBER = 1396547;
            public static final String DEFAULT_TX_ANTENNA_PORT = "PORT_2";
            public static final String DEFAULT_TX_EXPANDER_PORT = "NONE";
            public static final String DEFAULT_TRANSMIT_SOURCE = "INTERNAL";
            public static final String DEFAULT_DATA = "0x3000AE100000000000000036708062D3";

            private String type;
            private long timestamp;
            private long seqNum;
            private String txAntennaPort;
            private String txExpanderPort;
            private String transmitSource;
            private String data;

            /**
             * Gets the value of type
             *
             * @return the value of type
             */
            public String getType() {
                return type;
            }

            /**
             * Gets the value of timestamp
             *
             * @return the value of timestamp
             */
            public long getTimestamp() {
                return timestamp;
            }

            /**
             * Gets the value of seqNum
             *
             * @return the value of seqNum
             */
            public long getSeqNum() {
                return seqNum;
            }

            /**
             * Gets the value of txAntennaPort
             *
             * @return the value of txAntennaPort
             */
            public String getTxAntennaPort() {
                return txAntennaPort;
            }

            /**
             * Gets the value of txExpanderPort
             *
             * @return the value of txExpanderPort
             */
            public String getTxExpanderPort() {
                return txExpanderPort;
            }

            /**
             * Gets the value of transmitSource
             *
             * @return the value of transmitSource
             */
            public String getTransmitSource() {
                return transmitSource;
            }

            /**
             * Gets the data
             *
             * @return the data
             */
            public String getData() {
                return data;
            }

            /**
             * Sets the value of type
             *
             * @param type the new value of type
             * @return the TagReadDataMessageBuilder
             */
            public TagReadDataMessageBuilder setType(final String type) {
                this.type = type;
                return this;
            }

            /**
             * Sets the value of timestamp
             *
             * @param timestamp the new value of timestamp
             * @return the TagReadDataMessageBuilder
             */
            public TagReadDataMessageBuilder setTimestamp(final long timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            /**
             * Sets the value of seqNum
             *
             * @param seqNum the new value of seqNum
             * @return the TagReadDataMessageBuilder
             */
            public TagReadDataMessageBuilder setSeqNum(final long seqNum) {
                this.seqNum = seqNum;
                return this;
            }

            /**
             * Sets the value of txAntennaPort
             *
             * @param txAntennaPort the new value of txAntennaPort
             * @return the TagReadDataMessageBuilder
             */
            public TagReadDataMessageBuilder setTxAntennaPort(final String txAntennaPort) {
                this.txAntennaPort = txAntennaPort;
                return this;
            }

            /**
             * Sets the value of txExpanderPort
             *
             * @param txExpanderPort the new value of txExpanderPort
             * @return
             */
            public TagReadDataMessageBuilder setTxExpanderPort(final String txExpanderPort) {
                this.txExpanderPort = txExpanderPort;
                return this;
            }

            /**
             * Sets the value of transmitSource
             *
             * @param transmitSource the new value of transmitSource
             * @return the TagReadDataMessageBuilder
             */
            public TagReadDataMessageBuilder setTransmitSource(final String transmitSource) {
                this.transmitSource = transmitSource;
                return this;
            }

            /**
             * Sets the value of data.
             *
             * @param data the new value of data
             * @return the TagReadDataMessageBuilder
             */
            public TagReadDataMessageBuilder setData(final String data) {
                this.data = data;
                return this;
            }

            /**
             * Sets all values with default values.
             *
             * @return the TagReadDataMessageBuilder
             */
            public TagReadDataMessageBuilder setDefaultValues() {
                type = DEFAULT_TYPE;
                timestamp = DEFAULT_TIMESTAMP;
                seqNum = DEFAULT_SEQ_NUMBER;
                txAntennaPort = DEFAULT_TX_ANTENNA_PORT;
                txExpanderPort = DEFAULT_TX_EXPANDER_PORT;
                transmitSource = DEFAULT_TRANSMIT_SOURCE;
                data = DEFAULT_DATA;
                return this;
            }

            /**
             * Set all values with random values.
             *
             * @return the TagReadDataMessageBuilder
             */
            public TagReadDataMessageBuilder setRandomValues() {
                return this.setDefaultValues();
            }

            /**
             * Builds a TagReadDataMessage.
             *
             * @return the TagReadDataMessage
             */
            public TagReadDataMessage build() {
                return new TagReadDataMessage(this);
            }
        }
    }

    /**
     * StarFlexDataBuilder class,
     */
    public static class StarFlexDataBuilder {
        public static final int DEFAULT_MAX_TAG_READ_DATA_MESSAGE = 1000;
        public static final int DEFAULT_TAG_READ_DATA_MESSAGE_NUMBER = 500;
        public static final String DEFAULT_PREFIX_DATA = "TB";

        public static AtomicInteger COUNTER = new AtomicInteger(1);
        private String MacId;
        private List<TagReadDataMessage> tagReadDataMessages;

        /**
         * Gets the value of macId
         *
         * @return the value of macId
         */
        public String getMacId() {
            return MacId;
        }

        /**
         * Gets the list of tagReadDataMessages.
         *
         * @return the list of tagReadDataMessage
         */
        public List<TagReadDataMessage> getTagReadDataMessages() {
            return tagReadDataMessages;
        }

        /**
         * Sets the value of macId.
         *
         * @param macId the new value of macId
         * @return the StarFlexDataBuilder
         */
        public StarFlexDataBuilder setMacId(final String macId) {
            MacId = macId;
            return this;
        }

        /**
         * Sets the list of tagReadDataMessages.
         *
         * @param tagReadDataMessages the new list of tagReadDataMessages
         * @return the StarFlexDataBuilder
         */
        public StarFlexDataBuilder setTagReadDataMessages(final List<TagReadDataMessage> tagReadDataMessages) {
            this.tagReadDataMessages = tagReadDataMessages;
            return this;
        }

        /**
         * Sets all values with default values.
         *
         * @return the StarFlexDataBuilder
         */
        public StarFlexDataBuilder setDefaultValues() {
            // Reset the counter
            return setRandomValues(DEFAULT_MAX_TAG_READ_DATA_MESSAGE, DEFAULT_TAG_READ_DATA_MESSAGE_NUMBER, DEFAULT_PREFIX_DATA);
        }

        /**
         * Sets all values with random values.
         *
         * @param tagReadDataMessageNumber the value of tagReadDataMessageNumber
         * @return the StarFlexDataBuilder
         */

        public StarFlexDataBuilder setRandomValues(final int maxTagReadDataMessage, final int tagReadDataMessageNumber, final String prefixTagReadDataMessage) {
            if (COUNTER.get() > maxTagReadDataMessage) COUNTER.set(1);
            TagReadDataMessage tagReadDataMessage;
            List<TagReadDataMessage> dataMessages = new LinkedList<TagReadDataMessage>();
            for (int index = 0; index < tagReadDataMessageNumber; index++) {
                tagReadDataMessage = new TagReadDataMessage.TagReadDataMessageBuilder().setDefaultValues().setTimestamp(System.currentTimeMillis()).setData(String.format(TagReadDataMessage.TagReadDataMessageBuilder.DATA_FORMAT, prefixTagReadDataMessage, COUNTER.get())).build();
                dataMessages.add(tagReadDataMessage);
                COUNTER.getAndIncrement();
            }
            this.tagReadDataMessages = dataMessages;
            return this;
        }

        /**
         * Builds a StarFlexData
         *
         * @return the StarFlexData
         */
        public StarFlexData build() {
            return new StarFlexData(this);
        }


    }
}
