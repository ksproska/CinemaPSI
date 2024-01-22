CREATE TABLE "MOVIES"
(
    "MOVIE_ID"    BIGINT        NOT NULL,
    "TITLE"       VARCHAR(255)  NOT NULL,
    "DESCRIPTION" VARCHAR(2000) NOT NULL,
    "DIRECTOR"    VARCHAR(1000) NOT NULL,
    "SCRIPT"      VARCHAR(1000) NOT NULL,
    "ACTORS"      VARCHAR(1000) NOT NULL,
    "DURATION"    INT           NOT NULL,
    "RATING"      NUMERIC,
    "IMAGE"       VARCHAR(2000)

);
ALTER TABLE "MOVIES"
    ADD CONSTRAINT "MOVIES_PK" PRIMARY KEY ("MOVIE_ID");
INSERT INTO "MOVIES"
VALUES (1, 'The Spectral Voyage',
        'A mind-bending sci-fi thriller that takes you on a journey through time and alternate dimensions. As a team of scientists discovers a way to manipulate reality, they must race against time to prevent a catastrophe that could unravel the fabric of the universe. With stunning visuals and a gripping storyline, this film will keep you on the edge of your seat. Explore the mysteries of the multiverse as you witness the consequences of tampering with the very fabric of reality. Brace yourself for a mind-bending experience unlike anything you''ve seen before!',
        'Christopher Nolan', 'Christopher Nolan', 'Andrew Stark, Michelle Obama', 102, '4.2',
        'https://drive.google.com/file/d/12YWTSnZet-p1H0WG8XfCaGOhJldmLr8P/view?usp=sharing'),
       (2, 'The Forgotten Kingdom',
        'In a world divided by magic and technology, a young princess sets out on a quest to reunite her fractured kingdom. Along the way, she encounters mythical creatures, battles dark forces, and learns the true power of unity. This epic fantasy adventure will transport you to a breathtaking realm filled with wonder and danger. Join the princess as she embarks on an extraordinary journey of self-discovery and redemption. With stunning visuals, captivating storytelling, and unforgettable characters, this film will enchant audiences of all ages.',
        'Ava DuVernay', 'Ava DuVernay, Dan Turk', 'Kamila Sproska, Nikodem Kropielnicki', 99, '4.9', ''),
       (3, 'Echoes of Yesterday',
        'In a small town haunted by a tragic past, a young girl discovers a hidden portal that allows her to communicate with the spirits of the deceased. As she unravels the mysteries of the town''s history, she must confront her own demons and find redemption. With its atmospheric cinematography and haunting narrative, this film will take you on an emotional journey through the power of loss, forgiveness, and healing. Prepare to be captivated by the gripping tale of a girl''s quest for closure amidst a town shrouded in darkness.',
        'Guillermo del Toro', 'Guillermo del Toro', 'Guillermo del Toro, Maciek K.', 140, '3.2', ''),
       (4, 'The Guardian''s Legacy',
        'In a world where mythical creatures and humans coexist, a young guardian must protect an ancient artifact that holds the key to preserving peace. Joined by a motley crew of allies, they embark on a perilous quest filled with magic, danger, and unexpected alliances. This epic fantasy adventure will transport you to a breathtaking realm where legends come to life. Brace yourself for an unforgettable journey as the fate of the world hangs in the balance.',
        'Steven Spielberg', 'Steven Spielberg', 'Anita Kolanko, Martin Steward', 76, '4.8', ''),
       (5, 'The Enigma Code',
        'Based on a true story, ''The Enigma Code'' follows the remarkable journey of a team of codebreakers during World War II. As they strive to decipher the seemingly unbreakable Enigma code used by the German military, they face countless challenges, personal sacrifices, and race against time to turn the tide of the war. This gripping historical drama brings to life the incredible bravery and intellect of the individuals who played a crucial role in changing the course of history. Prepare to be captivated by the intensity and suspense of this high-stakes battle of wits.',
        'Kathryn Bigelow', 'Aneta Bigelow', 'Marian Stańczyk, Kacper Morawski', 134, '4.6', ''),
       (6, 'The Secret of Avalon',
        'Journey to the mystical land of Avalon in this enchanting tale of adventure and self-discovery. When a young girl stumbles upon a hidden portal, she is transported to a world filled with magic, mythical creatures, and ancient prophecies. As she embarks on a quest to unravel the secret of Avalon, she must confront her fears, make unlikely alliances, and embrace her destiny. With breathtaking visuals and a heartfelt story, this film will transport you to a realm where anything is possible.',
        'Greta Gerwig', 'Greta Gerwig', 'Bogusława Tlołka, Joanna Puciłowska', 160, '5.0', ''),
       (7, 'The Quantum Paradox',
        'Dive into the mind-bending world of quantum physics in ''The Quantum Paradox.'' This gripping thriller follows a brilliant physicist who discovers a way to manipulate the fabric of reality using quantum technology. As his experiments take an unexpected turn, he finds himself trapped in a dangerous game where the boundaries of space, time, and morality blur. Brace yourself for a mind-bending exploration of the nature of existence, consciousness, and the consequences of playing with the fundamental forces of the universe.',
        'Denis Villeneuve', 'Denis Villeneuve', 'Denis Villeneuve, Daniel Fąka', 89, '4.7', '');

CREATE TABLE "MOVIE_OFFER"
(
    "OFFER_ID"    BIGINT    NOT NULL,
    "DATE_SINCE"  TIMESTAMP NOT NULL,
    "DATE_UNTILL" TIMESTAMP NOT NULL,
    "MOVIE_ID"    BIGINT    NOT NULL
);
ALTER TABLE "MOVIE_OFFER"
    ADD CONSTRAINT "MOVIE_OFFER_PK" PRIMARY KEY ("OFFER_ID");
INSERT INTO "MOVIE_OFFER"
VALUES (1, '2023-12-19 00:00:00', '2024-01-31 00:00:00', 1)
     , (2, '2023-12-19 00:00:00', '2024-01-31 00:00:00', 2)
     , (3, '2023-12-19 00:00:00', '2024-01-31 00:00:00', 3)
     , (4, '2023-12-19 00:00:00', '2024-01-31 00:00:00', 4)
     , (5, '2023-12-19 00:00:00', '2024-01-31 00:00:00', 5)
     , (6, '2023-12-19 00:00:00', '2024-01-31 00:00:00', 6)
     , (7, '2023-12-19 00:00:00', '2024-01-31 00:00:00', 7);


CREATE TABLE "LANGUAGE_VERSION"
(
    "VERSION_ID"   BIGINT NOT NULL,
    "DUBBING"      VARCHAR(3),
    "LECTOR"       VARCHAR(3),
    "SUBTITLES"    VARCHAR(3),
    "VERSION_NAME" VARCHAR(256)
);
ALTER TABLE "LANGUAGE_VERSION"
    ADD CONSTRAINT "LANGUAGE_VERSION_PK" PRIMARY KEY ("VERSION_ID");
INSERT INTO "LANGUAGE_VERSION"
VALUES (1, 'PL', '', '', 'DUBBING PL')
     , (2, '', '', 'PL', 'NAPISY PL')
     , (3, 'PL', '', 'UA', 'DUBBING PL, NAPISY UA');

CREATE TABLE "VERSION_OFFER_MOVIE_MAP"
(
    "VERSION_OFFER_MOVIE_ID" BIGINT NOT NULL,
    "MOVIE_ID"               BIGINT NOT NULL,
    "VERSION_ID"             BIGINT NOT NULL,
    "OFFER_ID"               BIGINT NOT NULL
);

ALTER TABLE "VERSION_OFFER_MOVIE_MAP"
    ADD CONSTRAINT "VERSION_OFFER_MOVIE_MAP_PK" PRIMARY KEY ("VERSION_OFFER_MOVIE_ID");
INSERT INTO "VERSION_OFFER_MOVIE_MAP"
VALUES (1, 1, 1, 1)
     , (2, 1, 3, 1)
     , (3, 2, 1, 2)
     , (4, 2, 2, 2)
     , (5, 3, 1, 3)
     , (6, 4, 2, 4)
     , (7, 5, 3, 5)
     , (8, 6, 1, 6)
     , (9, 7, 2, 7);

CREATE TABLE "GENRES"
(
    "GENRE_ID"   BIGINT       NOT NULL,
    "GENRE_NAME" VARCHAR(100) NOT NULL
);

ALTER TABLE "GENRES"
    ADD CONSTRAINT "GENRES_PK" PRIMARY KEY ("GENRE_ID");
INSERT INTO "GENRES"
VALUES (1, 'Akcja')
     , (2, 'Komedia')
     , (3, 'Dramat')
     , (4, 'Horror')
     , (5, 'Science Fiction')
     , (6, 'Fantasy')
     , (7, 'Romans')
     , (8, 'Animacja')
     , (9, 'Dokumentalny')
     , (10, 'Thriller');

CREATE TABLE "MOVIES_GENRES_MAP"
(
    "GENRE_ID" BIGINT NOT NULL,
    "MOVIE_ID" BIGINT NOT NULL
);

INSERT INTO "MOVIES_GENRES_MAP"
VALUES (5, 1)
     , (10, 1)
     , (6, 2)
     , (3, 2)
     , (6, 3)
     , (6, 4)
     , (9, 5)
     , (7, 5)
     , (6, 6)
     , (10, 7);

CREATE TABLE "PRICES"
(
    "PRICE_ID"           BIGINT    NOT NULL,
    "BASE_PRICE"         REAL      NOT NULL,
    "REDUCTION_PCT"      BIGINT    NOT NULL,
    "PROMOTION_PCT"      BIGINT    NOT NULL,
    "PROMOTION_MIN_DAYS" BIGINT    NOT NULL,
    "DATE_SINCE"         TIMESTAMP NOT NULL,
    "DATE_UNTILL"        TIMESTAMP NOT NULL
);
ALTER TABLE "PRICES"
    ADD CONSTRAINT "PRICES_PK" PRIMARY KEY ("PRICE_ID");
INSERT INTO "PRICES"
VALUES (1, 16.99, 10, 5, 3, '2023-05-19 23:00:00', '2023-12-19 00:00:00')
     , (2, 20.99, 10, 5, 3, '2023-12-19 00:00:00', '2024-06-01 00:00:00');

CREATE TABLE "CINEMAS"
(
    "CINEMA_ID"    BIGINT        NOT NULL,
    "LOCALIZATION" VARCHAR(1000) NOT NULL
);
ALTER TABLE "CINEMAS"
    ADD CONSTRAINT "CINEMA_PK" PRIMARY KEY ("CINEMA_ID");
INSERT INTO "CINEMAS"
VALUES (1, 'Wrocław Pasaż Grunwaldzki')
     , (2, 'Wrocław Wroclavia')
     , (3, 'Warszawa Złote Tarasy');
