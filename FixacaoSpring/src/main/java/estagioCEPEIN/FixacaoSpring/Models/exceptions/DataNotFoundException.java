package estagioCEPEIN.FixacaoSpring.Models.exceptions;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String objeto) {
        super(objeto + " NÃO ENCONTRADO");
    }
}
