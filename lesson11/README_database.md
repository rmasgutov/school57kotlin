### База данных для личного поректа  


```sql

CREATE table Users
(
    id primary key,
    Login VARCHAR(255) NOT NULL UNIQUE,
    Firstname VARCHAR(255),
    Secondnamse VARCHAR(255),
    Age INT,
    Token INT,
    Password VARCHAR(255),
    Gender VARCHAR(6),  -- male / female
)



CREATE table Reactions
(
    id primary key,
    Tipe_of_reaction VARCHAR(7),  -- like / dislike
    Sender_id INT NOT NULL,
    Recipient_id INT NOT NULL
)
```