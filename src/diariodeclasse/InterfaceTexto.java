/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariodeclasse;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author felipe.reblin
 */
public class InterfaceTexto {
    
    private Diario diario;
    private Scanner reader;
    
    public InterfaceTexto(){
        this.reader = new Scanner(System.in);
        reader.useDelimiter("\\n");
    }
    
    public void executa(){
       boolean sair = false;
         
       inicializaDiario();
       
       while(!sair) {
            imprimeMenu();
        
            String opcao = reader.next();
                        
            if(opcao.equals("1")){
                cadastrarAluno();
            }else if(opcao.equals("2")){
                cadastrarExame();
            }else if(opcao.equals("3")){
                listaNotasAlunos();
            }else if(opcao.equals("4")){
                mostrarDiario();
            }else if(opcao.equals("5")){
                sair = true;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
    
    private void inicializaDiario() {
        System.out.println("Diário de Classe");
        System.out.println();
        System.out.print("Entre o nome da disciplina: ");
        String disciplina = reader.next();
        
        diario = new Diario(disciplina);
    }
    
    public void imprimeMenu(){
        System.out.println("DIÁRIO DE CLASSE");
        System.out.println("1 - Cadastrar Aluno");
        System.out.println("2 - Cadastrar Exame");
        System.out.println("3 - Lista notas do Aluno");
        System.out.println("4 - Mostrar diário");
        System.out.println("5 - SAIR");
    }
    
    private void cadastrarAluno() {
        System.out.println("---CADASTRO DE ALUNO---");
        System.out.println("Digite o nome do aluno");
        String nome = reader.next();
        System.out.println("Digite a matrícula do aluno");
        int matricula = reader.nextInt();
        System.out.println("Digite a data de nascimento do aluno");
        String dataNascimento = reader.next();
        diario.addAluno(new Aluno(nome, matricula, dataNascimento));
        System.out.println("Aluno cadastrado");
    }
    
    private void listarAlunos() {
        System.out.println("Lista de alunos:");
        
        for (Aluno aluno : diario.getAlunos().values()) {
            System.out.println(aluno.getMatricula() + " - " + aluno.getNome());
        }
    }
    
    private void cadastrarExame(){
        Aluno alunoSelecionado;
        System.out.println("---CADASTRO DE EXAME---");
        listarAlunos();
        System.out.println("Digite a matrícula do aluno");
        int matricula = reader.nextInt();
        
        alunoSelecionado = diario.getAluno(matricula);
        
        if (alunoSelecionado == null) {
            System.out.println("Aluno inválido");   
        } else {        
            System.out.println("Digite a data");
            String data = reader.next();
            System.out.println("Digite a nota do aluno");
            double nota = reader.nextDouble();
            diario.addExame(new Exame(alunoSelecionado, data, nota));
            System.out.println("Exame cadastrado");
        }
    }
    
    private void listaNotasAlunos(){
        Aluno alunoSelecionado;
        listarAlunos();
        System.out.println("Digite a matrícula do aluno");
        int matricula = reader.nextInt();
        
        alunoSelecionado = diario.getAluno(matricula);
        
        if (alunoSelecionado == null) {
            System.out.println("Aluno inválido");   
        } else {       
            System.out.println(diario.getNotasAluno(matricula));
        }
    }
    
    
    private void mostrarDiario(){
        System.out.println("---------------------------------------");
        System.out.println("DIÁRIO DE CLASSE");
        System.out.println("Disciplia: " + diario.getDisciplina());
        System.out.println("---------------------------------------");
        for( int matricula : diario.getDiario().keySet() )  {
            System.out.print(diario.getAluno(matricula).getNome() + "\t");
            // diario.getDiario().get(matricula) -> retorna todos os exames cuja matrícula é "matricula"
            for(Exame exame : diario.getDiario().get(matricula)) {
                System.out.print(" | " + exame.getNota());
            }
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }
}
