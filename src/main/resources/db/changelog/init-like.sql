CREATE TABLE like(
    id bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    user_id bigint REFERENCES user(id) ON DELETE CASCADE,
    post_id bigint REFERENCES post(id) ON DELETE CASCADE
)