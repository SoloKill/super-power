create table match (
  date_of_match                 timestamp,
  player1wins                   integer,
  player2wins                   integer
);

create table player (
  id                            bigint auto_increment not null,
  player_tag                    varchar(255),
  player_name                   varchar(255),
  constraint pk_player primary key (id)
);

create table tournament (
  tournament_name               varchar(255),
  tournament_region             varchar(255)
);

