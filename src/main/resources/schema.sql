CREATE TABLE IF NOT EXISTS habit (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description varchar(255) NOT NULL,
    time_to_complete TIMESTAMP NOT NULL,
    due_date DATE NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS habit_entries (
     habit_id BIGINT NOT NULL,
     scheduled_date TIMESTAMP NOT NULL,
     is_completed BOOLEAN NOT NULL DEFAULT FALSE,
     FOREIGN KEY (habit_id) REFERENCES habit (id) ON DELETE CASCADE
);