package Model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

      public static void main(String[] args) {
      String caminhosSalas ="codigo//codigos//Dao//arquivos//sala.txt";
      String caminhosUsuarios = "codigo//codigos//Dao//arquivos//usuarios.txt";
      String caminhosReservas = "codigo//codigos//Dao//arquivos//reservas.txt";
      File arquivoSalas = new File(caminhosSalas);
      File arquivoUsuarios =  new File(caminhosUsuarios);
      File arquivoReservas = new File(caminhosReservas);
      ArrayList<Sala> listaSalas = new ArrayList<>();
      ArrayList<Usuario> listaUsuarios = new ArrayList<>();
      ArrayList<Reserva>listaReservas = new ArrayList<>();
      leituraArquivo(arquivoSalas, arquivoUsuarios, arquivoReservas ,listaSalas, listaUsuarios, listaReservas);
      Scanner teclado= new Scanner(System.in);
      boolean houveAlteracao = false;
      int escolha = 0;
         do{
            System.out.println("Bem vindo ao roombookings");
            System.out.printf("Digite 1 para cadastrar uma sala.\n Digite 2 para cadastrar o usuario.\n Digite 3 para reservar uma sala.\n Digite 4 para cancelar uma reserva.\n Digite 0 para sair.\n");
            try{
            escolha = teclado.nextInt();
            }catch(Exception e){
               System.out.println(e.getMessage());
               teclado.nextLine();
            }
            switch (escolha) {
               case 1:
               cadastrarSala(listaSalas,teclado);
                  houveAlteracao= true;
                     
                     break;
               case 2:
               cadastrarUsuario(listaUsuarios,teclado);
               houveAlteracao= true;
                     break;
               case 3:
                     reservarSala(listaReservas,listaSalas,listaUsuarios,teclado);
                     houveAlteracao= true;
                     break;
               case 4:
                     cancelarReserva(listaReservas,listaSalas,listaUsuarios,teclado);
                     houveAlteracao = true;
                     break;   
               case 0:
                     break;         
               default:
                     System.out.println("Digite uma opção valida!!");
                  break;
            }
         }while(escolha!=0);
         if (houveAlteracao) {
            escritaArquivos(listaSalas, listaUsuarios, listaReservas ,arquivoSalas, arquivoUsuarios, arquivoReservas);
            System.out.println("Dados salvos com sucesso!");
        } else {
            System.out.println("Nenhuma alteração foi feita. Nada salvo.");
        }
         teclado.close();

      }

      public static void reservarSala(ArrayList<Reserva>listaReservas,ArrayList<Sala>listaSalas, ArrayList<Usuario>listaUsuarios,Scanner teclado){
         System.out.println("Tipos de salas:");
         for(Sala sala:listaSalas){
            System.out.println(sala.getCodigoSala() +"\t"+ sala.getCapacidade() +"\t"+ sala.getClass().getSimpleName());
         }
         teclado.nextLine();
         System.out.println("Digite o codígo da sala que você deseja reservar: ");
         String codigoSala = teclado.nextLine();
         System.out.println("Qual o seu cpf de usuario");
         String cpf = teclado.nextLine();
         Usuario procurado = null;
         for (Usuario usuario : listaUsuarios) {
            if(usuario.getCpf().equals(cpf)){
               procurado = usuario;
            }
         }
         if(procurado == null){
            System.out.println("Usuario não encontrado");
            return;
         }
         for(Sala sala:listaSalas){
            if(codigoSala.equals(sala.getCodigoSala())){
               System.out.println("Qual ano você dejesa? ");
               int anoInicio = teclado.nextInt();
               System.out.println("Qual o mês você deseja? ");
               int mesInicio = teclado.nextInt();
               System.out.println("Qual o dia você deseja? ");
               int diaInicio = teclado.nextInt();
               System.out.println("Qual a hora de inicio da reserva? ");
               int horaInicio = teclado.nextInt();
               System.out.println("Qual o minuto de inicio da reserva? ");
               int minutoInicio = teclado.nextInt();
               //Termino da sala
               System.out.println("Qual ano de termino você deseja? ");
               int anoTermino = teclado.nextInt();
               System.out.println("Qual o mês de termino você deseja? ");
               int mesTermino = teclado.nextInt();
               System.out.println("Qual o dia de termino você deseja? ");
               int diaTermino = teclado.nextInt();
               System.out.println("Qual a hora de termino da reserva? ");
               int horaTermino = teclado.nextInt();
               System.out.println("Qual o minuto de termino da reserva? ");
               int minutoTermino = teclado.nextInt();
               Reserva novaReserva = new Reserva(procurado, sala ,LocalDateTime.of(anoInicio,  mesInicio, diaInicio, horaInicio, minutoInicio, 0), LocalDateTime.of(anoTermino, mesTermino, diaTermino, horaTermino, minutoTermino, 0));
               novaReserva.addReserva(listaReservas,novaReserva);
               try{
               System.out.println("Preço da sua reserva:" + novaReserva.calcularPreco(novaReserva) );
               } catch(Exception e){
                  e.printStackTrace();
               }
         }
         }
      }

      public static void cancelarReserva(ArrayList<Reserva>listaReservas,ArrayList<Sala> listaSalas, ArrayList<Usuario> listaUsuarios,Scanner teclado){
      teclado.nextLine();
      System.out.println("Qual o seu cpf");
      String cpf = teclado.nextLine();
      Usuario procurado = null;
      for (Usuario usuario : listaUsuarios) {
         if(usuario.getCpf().equals(cpf)){
            procurado = usuario;
         }
      }
      if (procurado == null){
         System.out.println("Usuario não encontrado");
         return;
      }
      System.out.println("Suas reservas:");
         for (Reserva reserva : listaReservas){
            if(reserva.getUsuario().getCpf().equals(procurado.getCpf())){
               System.out.println("id "+ reserva.getId());
               System.out.println("data de inicio: " + reserva.getDataInicio());
               System.out.println("data de fim: " + reserva.getDataFim());
               System.out.println();
            }
         }

      System.out.println("Qual id da reserva que você deseja cancelar? ");
      int id = teclado.nextInt();
      boolean reservaEncontrada = false;
         for (Reserva reserva : listaReservas) {
            if (reserva.getId() == id && reserva.getUsuario().getCpf().equals(procurado.getCpf())) {
               double estorno = reserva.removeReserva(listaReservas);
               System.out.printf("Valor estornado: R$ %.2f%n", estorno);
               reservaEncontrada = true;
               break;
            }
         }

         if (!reservaEncontrada) {
            System.out.println("Reserva não encontrada ou não pertence a este usuário.");
         }
         }

      public static void cadastrarUsuario(ArrayList<Usuario> ListaUsuarios,Scanner teclado){
         teclado.nextLine();
         System.out.println("Digite o seu nome:");
         String nome = teclado.nextLine();
         System.out.println("Digite o seu CPF:");
         String cpf = teclado.nextLine();
         
         System.out.println("Digite o CEP:");
         String cep = teclado.nextLine();
         System.out.println("Digite o nome da Rua:");
         String rua = teclado.nextLine();
         System.out.println("Digite o numero:");
         String numero = teclado.nextLine();
         System.out.println("Digite a cidade:");
         String cidade = teclado.nextLine();
         System.out.println("Digite o pais:");
         String pais = teclado.nextLine(); 
         Endereco endereco = new Endereco(cep, rua, numero, cidade, pais);
         
         System.out.println("Digite 1 se for convidado");
         int res = teclado.nextInt();
         teclado.nextLine();
         boolean convidado= false;
         if(res == 1){
            convidado = true;
            Usuario usuario = new Usuario(nome,convidado );
            ListaUsuarios.add(usuario);
         }
         else{
         System.out.println("Digite 1 para usuario corporativo ou 0 se nao for usuario corporativo");
         int resposta = teclado.nextInt();
         boolean corporativo= false;
         if(resposta==1){
            corporativo= true;
         }
         Usuario usuario = new Usuario(nome,cpf,endereco,corporativo);
         ListaUsuarios.add(usuario);
      }
   }

      public static void cadastrarSala(ArrayList<Sala> listaSalas,Scanner teclado){
         teclado.nextLine();
         System.out.println("Digite o codigo da Sala. (Ele deve ter 6 caracteres e começar com 3 letras e terminar com 3 numeros.)");
         String codigoSala = teclado.nextLine();
         System.out.println("Digite a capacidade da sala:");
         int capacidade = teclado.nextInt();
         teclado.nextLine();
         
         System.out.println("Digite o CEP:");
         String cep = teclado.nextLine();
         System.out.println("Digite o nome da Rua:");
         String rua = teclado.nextLine();
         System.out.println("Digite o numero:");
         String numero = teclado.nextLine();
         System.out.println("Digite a cidade:");
         String cidade = teclado.nextLine();
         System.out.println("Digite o pais:");
         String pais = teclado.nextLine();
         Endereco endereco = new Endereco(cep, rua, numero, cidade, pais);

         
         System.out.printf("Digite 1 para Sala Premium\n Digite 2 para Sala Vip\n Digite 3 para Sala Standard\n");
         int tipo = teclado.nextInt();
         teclado.nextLine();
         
         try{
         switch (tipo) {
               case 1:
                  listaSalas.add(new SalaPremium(codigoSala, capacidade, tipo,endereco));
                  break;
               case 2:
                  listaSalas.add(new SalaVip(codigoSala, capacidade,tipo ,endereco));
                  break;
               case 3:
                  listaSalas.add(new SalaStandard(codigoSala, capacidade,tipo ,endereco));   
                  break;
            default:
               teclado.close();
               throw new AssertionError("Escolha invalida!!");
         }
      }catch(Exception e){
         System.out.println(e.getMessage());
      }
      }

      public static void escritaArquivos(ArrayList<Sala> listaSalas, ArrayList<Usuario> listaUsuarios, ArrayList<Reserva>listaReservas ,File arquivoSalas, File arquivoUsuarios,File arquivoReservas){
         try(BufferedWriter escritaSalas = new BufferedWriter(new FileWriter(arquivoSalas));
         BufferedWriter escritaUsuarios = new BufferedWriter(new FileWriter(arquivoUsuarios));
         BufferedWriter escritaReservas = new BufferedWriter(new FileWriter(arquivoReservas));){
            
            for (Usuario usuario : listaUsuarios) {
               escritaUsuarios.write(usuario.escritaArquivo());
               escritaUsuarios.newLine();
            }
            for (Sala sala : listaSalas) {
               escritaSalas.write(sala.escritaArquivo());
               escritaSalas.newLine();
               
            }
            for(Reserva reserva : listaReservas){
               escritaReservas.write(reserva.escritaArquivo());
               escritaReservas.newLine();
            }
            
         }catch(IOException e){
            System.out.println(e.getMessage());
         
         }
      }
   }