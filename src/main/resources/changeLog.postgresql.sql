--liquibase formatted sql

--changeset joseribeiro:20200801-1
create table stock (id integer generated by default as identity not null, ticker varchar(6) not null, constraint stock_pkey primary key (id));

--changeset joseribeiro:20200801-2
alter table stock add constraint stock_ticker_key unique (ticker);

--changeset joseribeiro:20200801-3
alter table stock add column market_cap bigint;
alter table stock add column price_to_earnings decimal(2, 2);
alter table stock add column ev_to_ebitda decimal(2, 2);
alter table stock add column current_ratio decimal(2, 2);
alter table stock add column gross_debt_to_net_worth decimal(2, 2);
alter table stock add column return_on_equity decimal(2, 2);
alter table stock add column dividend_yield decimal(2, 2);
alter table stock add column dividend_payout decimal(2, 2);

--changeset joseribeiro:20200805-1
alter table stock alter column price_to_earnings type decimal(4, 2);
alter table stock alter column ev_to_ebitda type decimal(4, 2);
alter table stock alter column current_ratio type decimal(4, 2);
alter table stock alter column gross_debt_to_net_worth type decimal(4, 2);
alter table stock alter column return_on_equity type decimal(4, 2);
alter table stock alter column dividend_yield type decimal(4, 2);
alter table stock alter column dividend_payout type decimal(4, 2);

--changeset joseribeiro:20200901-1
alter table stock alter column market_cap type decimal(14, 2);

--changeset joseribeiro:20200901-2
alter table stock alter column price_to_earnings type decimal(12, 2);
alter table stock alter column ev_to_ebitda type decimal(12, 2);
alter table stock alter column current_ratio type decimal(12, 2);
alter table stock alter column gross_debt_to_net_worth type decimal(12, 2);
alter table stock alter column return_on_equity type decimal(12, 2);
alter table stock alter column dividend_yield type decimal(12, 2);
alter table stock alter column dividend_payout type decimal(12, 2);
