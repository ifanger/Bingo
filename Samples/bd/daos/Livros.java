package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Livros
{
    public boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM LIVROS " +
                  "WHERE CODIGO = ?";

            BD.comando.prepareStatement (sql);

            BD.comando.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BD.comando.executeQuery ();

            retorno = resultado.first();

            /* // ou, se preferirmos,

            String sql;

            sql = "SELECT COUNT(*) AS QUANTOS " +
                  "FROM LIVROS " +
                  "WHERE CODIGO = ?";

            BD.comando.prepareStatement (sql);

            BD.comando.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BD.comando.executeQuery ();

            resultado.first();

            retorno = resultado.getInt("QUANTOS") != 0;

            */
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
        }

        return retorno;
    }

    public void incluir (Livro livro) throws Exception
    {
        if (livro==null)
            throw new Exception ("Livro nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO LIVROS " +
                  "(CODIGO,NOME,PRECO) " +
                  "VALUES " +
                  "(?,?,?)";

            BD.comando.prepareStatement (sql);

            BD.comando.setInt    (1, livro.getCodigo ());
            BD.comando.setString (2, livro.getNome ());
            BD.comando.setFloat  (3, livro.getPreco ());

            BD.comando.executeUpdate ();
            BD.comando.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir livro");
        }
    }

    public void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM LIVROS " +
                  "WHERE CODIGO=?";

            BD.comando.prepareStatement (sql);

            BD.comando.setInt (1, codigo);

            BD.comando.executeUpdate ();
            BD.comando.commit        ();        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir livro");
        }
    }

    public void alterar (Livro livro) throws Exception
    {
        if (livro==null)
            throw new Exception ("Livro nao fornecido");

        if (!cadastrado (livro.getCodigo()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE LIVROS " +
                  "SET NOME=? " +
                  "SET PRECO=? " +
                  "WHERE CODIGO = ?";

            BD.comando.prepareStatement (sql);

            BD.comando.setString (1, livro.getNome ());
            BD.comando.setFloat  (2, livro.getPreco ());
            BD.comando.setInt    (3, livro.getCodigo ());

            BD.comando.executeUpdate ();
            BD.comando.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de livro");
        }
    }

    public Livro getLivro (int codigo) throws Exception
    {
        Livro livro = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM LIVROS " +
                  "WHERE CODIGO = ?";

            BD.comando.prepareStatement (sql);

            BD.comando.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BD.comando.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            livro = new Livro (resultado.getInt   ("CODIGO"),
                               resultado.getString("NOME"),
                               resultado.getFloat ("PRECO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
        }

        return livro;
    }

    public MeuResultSet getLivros () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM LIVROS";

            BD.comando.prepareStatement (sql);

            resultado = (MeuResultSet)BD.comando.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar livros");
        }

        return resultado;
    }
}