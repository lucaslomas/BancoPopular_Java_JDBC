package entities;

public class Cliente {
    private String nome;
    private String email;

    private long cpf_Cnpj;
    ContaBancaria contaBancaria;

    TipoDePessoa tipoDePessoa;

    CpfCnpjValidator cpfCnpjValidator = new CpfCnpjValidator();

    public Cliente() {
    }
    public Cliente(String nome, String email, String cpf_Cnpj) {
        this.nome = nome;
        this.email = email;
        this.cpf_Cnpj = CPForCNPJ(cpf_Cnpj);
    }

    public Cliente(String nome, String email, TipoDePessoa tipoDePessoa, ContaBancaria contaBancaria) {
        this.nome = nome;
        this.email = email;
        this.contaBancaria = contaBancaria;
        this.tipoDePessoa = tipoDePessoa;
    }

    public long getCpf_Cnpj() {
        return cpf_Cnpj;
    }

    public void setCpf_Cnpj(String cpf_Cnpj) {
        this.cpf_Cnpj = CPForCNPJ(cpf_Cnpj);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public TipoDePessoa getTipoDePessoa() {
        return tipoDePessoa;
    }

    public void setTipoDePessoa(TipoDePessoa tipoDePessoa) {
        this.tipoDePessoa = tipoDePessoa;
    }
    public Boolean validacaoCPF(String cpf){
        return cpfCnpjValidator.isCpf(cpf);

    }

    public Boolean validacaoCNPJ(String cnpj){
        return cpfCnpjValidator.isCnpj(cnpj);
    }

  public long CPForCNPJ(String cpf_cnpj){
        if (validacaoCPF(cpf_cnpj)){
            tipoDePessoa = TipoDePessoa.FISICA;
            return Long.parseLong(cpf_cnpj);

        }
        if(validacaoCNPJ(cpf_cnpj)){
            tipoDePessoa = TipoDePessoa.JURIDICA;
            return Long.parseLong(cpf_cnpj);
        }
        else {
            throw new RuntimeException("Error!! Insira um CPF OU CNPJ valido!");
        }
  }

    @Override
    public String toString() {
        return "Cliente" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf_Cnpj=" + String.valueOf(cpf_Cnpj) +
                ", tipoDePessoa=" + tipoDePessoa;
    }
}
