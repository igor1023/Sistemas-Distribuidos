package engtelecom.std;

import com.google.rpc.context.AttributeContext;
import engtelecom.std.agenda.AgendaGrpc;
import engtelecom.std.agenda.Pessoa;
import engtelecom.std.agenda.Resposta;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.HashSet;

public class AgendaImpl extends AgendaGrpc.AgendaImplBase {

    private HashMap<Integer, Pessoa> agenda = new HashMap<>();

    @Override
    public void adicionar(Pessoa request, StreamObserver<Resposta> responseObserver) {

        String mensagem = "adicionado com sucesso";

        if(!this.agenda.containsKey(request.getId())){
            this.agenda.put(request.getId(), request);
        }else {
            mensagem = String.format("%d já existe", request.getId());
        }

        // new Resposta
        Resposta resposta = Resposta.newBuilder().setResultado(mensagem).build();
        responseObserver.onNext(resposta); //mandar resposta
        responseObserver.onCompleted(); // indica que já enviou (obrigatoria)

    }

    @Override
    public void buscar(Pessoa request, StreamObserver<Pessoa> responseObserver) {

        Pessoa p = this.agenda.get(request.getId());

        // se p for null, o gRPC nao envia null pela rede;
        // todos os atributos estarão com valor padrao
        // int por padrao é 0, entao pode ser uma alternativa para tratamento
        // de busca de contato nao existente
        responseObserver.onNext(p); //mandar resposta


        responseObserver.onCompleted(); // indica que já enviou (obrigatoria)
    }
}
