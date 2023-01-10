

-- CRIANDO O USUÁRIO
ALTER SESSION SET "_ORACLE_SCRIPT" = true;
CREATE USER appcadastrar IDENTIFIED BY appcadastrar DEFAULT TABLESPACE USERS;
GRANT connect, resource TO appcadastrar;
-- CRIANDO O USUÁRIO

-- TABELAS
CREATE TABLE EMPRESA
(
    ID NUMBER NOT NULL,
    NOME VARCHAR2(100),
    CNPJ VARCHAR2(18),
    DATA_ABERTURA DATE,
    CONSTRAINT TAB_EMPRESA_PK PRIMARY KEY
    (
        ID
    )
);

CREATE TABLE USERS_APPLICATION
(
    ID NUMBER NOT NULL,
    NOME VARCHAR2(100) NOT NULL,
    NOME_ACESSO VARCHAR2(50) NOT NULL,
    SENHA VARCHAR2(20) NOT NULL,
    CONSTRAINT TAB_USERS_APPLICATION_PK PRIMARY KEY
    (
        ID
    )
);
-- TABELAS

-- SEQUENCES
CREATE SEQUENCE SEQ_ID_EMPRESA
START WITH 1
INCREMENT BY 1
MAXVALUE 9999999999
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_ID_USERS
START WITH 1
INCREMENT BY 1
MAXVALUE 9999999999
NOCYCLE
NOCACHE;
-- SEQUENCES

-- PROCEDURES
CREATE OR REPLACE PROCEDURE CADASTRAR_EMPRESA
(
    p_NOME IN EMPRESA.NOME%type, 
    p_CNPJ IN EMPRESA.CNPJ%type, 
    p_DATAABERTURA IN EMPRESA.DATA_ABERTURA%type
)
IS
BEGIN
    INSERT INTO EMPRESA VALUES (SEQ_ID_EMPRESA.NEXTVAL, UPPER(p_NOME), FORMATA_CNPJ(p_CNPJ), p_DATAABERTURA);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, SQLERRM || ' Erro ao cadastrar Nova Empresa ');
END;

CREATE OR REPLACE PROCEDURE CADASTRAR_USUARIO
(
    p_NOME IN USERS_APPLICATION.NOME%type, 
    p_ACESSO IN USERS_APPLICATION.NOME_ACESSO%type, 
    p_SENHA IN USERS_APPLICATION.SENHA%type
)
IS
BEGIN
    INSERT INTO USERS_APPLICATION VALUES (SEQ_ID_USERS.NEXTVAL, UPPER(p_NOME), p_ACESSO, p_SENHA);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20003, SQLERRM || ' Erro ao cadastrar Novo Usuário ');
END;
-- PROCEDURES

-- FUNÇÕES
CREATE OR REPLACE FUNCTION FORMATA_CNPJ
(p_CNPJ IN EMPRESA.CNPJ%type)
RETURN EMPRESA.CNPJ%type
IS
BEGIN
    RETURN SUBSTR(p_CNPJ, 1, 2) || '.' || SUBSTR(p_CNPJ, 3, 3) || '.' || SUBSTR(p_CNPJ, 6, 3) || '/' || SUBSTR(p_CNPJ, 9, 4) || '-' || SUBSTR(p_CNPJ, 13);
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, SQLERRM || ' Erro ao formatar CNPJ ');
END;
-- FUNÇÕES

-- SINÔNIMOS
CREATE PUBLIC SYNONYM SEQ_ID_EMPRESA FOR SYSTEM.SEQ_ID_EMPRESA;
CREATE PUBLIC SYNONYM EMPRESA FOR SYSTEM.EMPRESA;
CREATE PUBLIC SYNONYM CADASTRAR_EMPRESA FOR SYSTEM.CADASTRAR_EMPRESA;
CREATE PUBLIC SYNONYM FORMATA_CNPJ FOR SYSTEM.FORMATA_CNPJ;
CREATE PUBLIC SYNONYM USERS_APPLICATION FOR SYSTEM.USERS_APPLICATION;
CREATE PUBLIC SYNONYM SEQ_ID_USERS FOR SYSTEM.SEQ_ID_USERS;
CREATE PUBLIC SYNONYM CADASTRAR_USUARIO FOR SYSTEM.CADASTRAR_USUARIO;
-- SINÔNIMOS

--GRANTS
GRANT EXECUTE ON CADASTRAR_EMPRESA TO APPCADASTRAR;
GRANT EXECUTE ON CADASTRAR_EMPRESA TO APPCADASTRAR;
GRANT EXECUTE ON FORMATA_CNPJ TO APPCADASTRAR;
GRANT SELECT ON EMPRESA TO APPCADASTRAR;
GRANT SELECT ON USERS_APPLICATION TO APPCADASTRAR
GRANT EXECUTE ON SYSTEM.CADASTRAR_EMPRESA_PAC TO APPCADASTRAR;;
--GRANTS

-- CABEÇALHO PACOTE
CREATE OR REPLACE PACKAGE CADASTRAR_EMPRESA_PAC
IS  
    PROCEDURE CADASTRAR_USUARIO
    (
        p_NOME IN USERS_APPLICATION.NOME%type, 
        p_ACESSO IN USERS_APPLICATION.NOME_ACESSO%type, 
        p_SENHA IN USERS_APPLICATION.SENHA%type
    );
    
    PROCEDURE CADASTRAR_EMPRESA
    (
        p_NOME IN EMPRESA.NOME%type, 
        p_CNPJ IN EMPRESA.CNPJ%type, 
        p_DATAABERTURA IN EMPRESA.DATA_ABERTURA%type
    );
END; 
-- CABEÇALHO PACOTE

-- BODY PACOTE
create or replace PACKAGE BODY CADASTRAR_EMPRESA_PAC -- NO CORPO DO PACOTE DEVE CONTER OS CÓDIGOS DOS OBJETOS
IS

    -- FUNÇÕES
    FUNCTION FORMATA_CNPJ
    (p_CNPJ IN EMPRESA.CNPJ%type)
    RETURN EMPRESA.CNPJ%type
    IS
    BEGIN
        RETURN SUBSTR(p_CNPJ, 1, 2) || '.' || SUBSTR(p_CNPJ, 3, 3) || '.' || SUBSTR(p_CNPJ, 6, 3) || '/' || SUBSTR(p_CNPJ, 9, 4) || '-' || SUBSTR(p_CNPJ, 13);
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20001, SQLERRM || ' Erro ao formatar CNPJ ');
    END;
    -- FUNÇÕES

    -- PROCEDURES
    PROCEDURE CADASTRAR_EMPRESA
    (
        p_NOME IN EMPRESA.NOME%type, 
        p_CNPJ IN EMPRESA.CNPJ%type, 
        p_DATAABERTURA IN EMPRESA.DATA_ABERTURA%type
    )
    IS
        v_CNPJF EMPRESA.CNPJ%type;
    BEGIN
        v_CNPJF := FORMATA_CNPJ(p_CNPJ);
        INSERT INTO EMPRESA VALUES (SEQ_ID_EMPRESA.NEXTVAL, UPPER(p_NOME), v_CNPJF, p_DATAABERTURA);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20002, SQLERRM || ' Erro ao cadastrar Nova Empresa ');
    END;

    PROCEDURE CADASTRAR_USUARIO
    (
        p_NOME IN USERS_APPLICATION.NOME%type, 
        p_ACESSO IN USERS_APPLICATION.NOME_ACESSO%type, 
        p_SENHA IN USERS_APPLICATION.SENHA%type
    )
    IS
    BEGIN
        INSERT INTO USERS_APPLICATION VALUES (SEQ_ID_USERS.NEXTVAL, UPPER(p_NOME), p_ACESSO, p_SENHA);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20003, SQLERRM || ' Erro ao cadastrar Novo Usuário ');
    END;
    -- PROCEDURES


END;
-- BODY PACOTE