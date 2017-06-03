package programa;

import bd.*;
import bd.dbos.*;

public class Programa
{
    public static void main(String[] args)
    {
        try
        {
            Livro livro = new Livro (1,"L'Etranger",55.55F);
            BD.livros.incluir (livro);
        }
        catch (Exception erro)
        {
            System.err.println (erro);
        }
    } 
}