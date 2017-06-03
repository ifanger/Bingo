package bd.dbos;

public class Livro
{
    private int    codigo;
    private String nome;
    private float  preco;
 
    public void setCodigo (int codigo) throws Exception
    {
        if (codigo <= 0)
            throw new Exception ("Codigo invalido");

        this.codigo = codigo;
    }   

    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }

    public void setPreco (float preco) throws Exception
    {
        if (preco <= 0)
            throw new Exception ("Preco invalido");

        this.preco = preco;
    }

    public int getCodigo ()
    {
        return this.codigo;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public float getPreco ()
    {
        return this.preco;
    }

    public Livro (int codigo, String nome, float preco) throws Exception
    {
        this.setCodigo (codigo);
        this.setNome   (nome);
        this.setPreco  (preco);
    }

    // é claro que os métodos obrigatórios deveriam ser feitos
    // para a implementação ficar completa
}