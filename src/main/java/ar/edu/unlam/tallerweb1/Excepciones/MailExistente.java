package ar.edu.unlam.tallerweb1.Excepciones;

public class MailExistente extends RuntimeException{

    public MailExistente(String mensaje){
        super(mensaje);
    }

    public MailExistente(){
        super("Este email, ya se encuentra registrado en el sistema");
    }


}
