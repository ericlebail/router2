CREATE TABLE IF NOT EXISTS EVENT
(
    id        BIGINT NOT NULL,
    code      VARCHAR(255),
    status    smallint,
    date      DATE DEFAULT NOW(),
    user_code VARCHAR(255),
    user_uuid VARCHAR(255),
    payload   VARCHAR(255),
    CONSTRAINT pk_event PRIMARY KEY (id)
);
