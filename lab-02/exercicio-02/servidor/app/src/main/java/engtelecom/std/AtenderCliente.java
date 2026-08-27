package engtelecom.std;

import module java.base;

public record AtenderCliente (Socket clientSocket) implements Runnable{

    @Override
    public void run() {

        var enderecoCliente = clientSocket.getInetAddress().getHostAddress();
        var portaCliente = clientSocket.getPort();

        System.out.printf("Cliente conectado: %s:%d%n", enderecoCliente, portaCliente);

        try(
                var dis = new DataInputStream(clientSocket.getInputStream());
                var dos = new DataOutputStream(clientSocket.getOutputStream())){

            String nomeArquivo = dis.readUTF();
            System.out.println("Arquivo solicitado: " + nomeArquivo);

            if (Files.exists(Path.of(nomeArquivo))) {
                // Se o arquivo existe, envie seu tamanho e conteúdo
                long tamanho = Files.size(Path.of(nomeArquivo));
                dos.writeLong(tamanho);
                dos.flush();
                Thread.sleep(2000);
                long bytesEnviados = Files.copy(Path.of(nomeArquivo), dos);
                dos.flush();
                System.out.printf("Enviado ao cliente %s:%d : %s (%d bytes)%n",
                        enderecoCliente, portaCliente, nomeArquivo, bytesEnviados);
            } else {
                // Se o arquivo não existe, envie -1 para indicar erro
                dos.writeLong(-1);
                dos.flush();
                System.out.println("Arquivo não encontrado: " + nomeArquivo);
            }

            System.out.printf("Conexão com o cliente %s:%d encerrada%n", enderecoCliente, portaCliente);

        }catch(Exception e){
            System.err.println("Error: " + e);
        }

    }

}