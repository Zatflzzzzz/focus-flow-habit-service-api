CREATE TABLE IF NOT EXISTS habit (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description varchar(255) NOT NULL,
    time_to_complete TIME NOT NULL,
    due_date TIMESTAMP NOT NULL,
    last_action TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL
);

CREATE TABLE habit_logs (
    id BIGSERIAL PRIMARY KEY,
    habit_id BIGINT NOT NULL,
    scheduled_date TIMESTAMP NOT NULL,
    is_completed BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (habit_id) REFERENCES habit (id) ON DELETE CASCADE
);