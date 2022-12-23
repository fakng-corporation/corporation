INSERT INTO authority (authority)
VALUES ('ROLE_USER');

-- password is nickname hashed with bcrypt algorithm
-- with salt abc and work factor 10
INSERT INTO "user" (nickname, email, password, about_me, enabled)
VALUES
    ('boba', 'boba@boba.com', '$2y$10$CS69QqPMwAYucGw3gqppy.5y.c83/QC5UcVjDUnWJWWo6SP70rMgG', 'I am boba!', TRUE),
    ('kate', 'kate@kate.com', '$2y$10$XpFnJQFaejBjJDIc2x9gtuvsS1KZfrfRMHareERGgmt9Kb35wSwFW', 'I am kate!', TRUE),
    ('mary', 'mary@mary.com', '$2y$10$dwaIa5Zz7nyIycSuq0Eybu5rI.bBFl3uoefdf3ZWEon.QN.c5TCs6', 'I am mary!', TRUE),
    ('olaf', 'olaf@olaf.com', '$2y$10$FFnaZvS4MdeA.DKPjqxZl.Xf3D2rWvsUeayU1irAtbTt1nPVIUmha', 'I am olaf!', TRUE),
    ('vova', 'vova@vova.com', '$2y$10$gZ6Nc7cf2.at2yQ8hxfQQuVtBBm3/YxNla92685x9rk6B11PFuz2G', 'I am vova!', FALSE);
