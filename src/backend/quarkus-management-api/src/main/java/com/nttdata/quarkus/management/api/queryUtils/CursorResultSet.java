package com.nttdata.quarkus.management.api.queryUtils;

import java.util.List;

public class CursorResultSet<T> {

    private List<T> items;
    private String nextCursor;

    private CursorResultSet(List<T> items, String nextCursor) {
        this.items = items;
        this.nextCursor = nextCursor;
    }

    public List<T> getItems() {
        return items;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public CursorResultSet(Builder builder) {
        this.items = builder.items;
        this.nextCursor = builder.nextCursor;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder<T> {

        public Builder() {}

        private List items;
        private String nextCursor ;

        public Builder items(List<T> items) {
            this.items = items;
            return this;
        }

        public Builder nextCursor(String nextCursor ) {
            this.nextCursor = nextCursor;
            return this;
        }

        public CursorResultSet create() {
            return new CursorResultSet(this);
        }

    }

}
