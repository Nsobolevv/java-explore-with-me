
CREATE TABLE IF NOT EXISTS hits (
    id bigint generated by default as identity not null,
    app VARCHAR(32) NOT NULL,
    uri VARCHAR(2048) NOT NULL,
    ip VARCHAR(16) NOT NULL,
    datetime timestamp WITHOUT TIME ZONE NOT NULL
);