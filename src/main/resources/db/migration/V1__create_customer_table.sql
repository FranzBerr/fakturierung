CREATE TABLE [dbo].[customer] (
                                  id            BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
                                  kundennummer  NVARCHAR(20)  NOT NULL UNIQUE,
                                  firma         NVARCHAR(50) NOT NULL,
                                  strasse       NVARCHAR(50) NOT NULL,
                                  plz           NVARCHAR(10)  NOT NULL,
                                  ort           NVARCHAR(50) NOT NULL
);
CREATE INDEX IX_customer_kundennummer ON [dbo].[customer](kundennummer);