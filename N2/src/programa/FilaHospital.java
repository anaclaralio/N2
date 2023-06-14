package programa;

import java.util.*;  

import javax.swing.JOptionPane;
 

public class FilaHospital {  

 

    public static void main(String[] args) {  

 

        FilaHospitalController controller = new FilaHospitalController();  

 

        controller.iniciarAtendimento();  

 

    }  

 

}  
class Paciente {  

	 

    private String nome;  

 

    private String senha;  

 

    private TipoAtendimento tipoAtendimento;  

 

    private EspecialidadeMedica especialidade;  

 

  

 

    public Paciente(String nome, String senha, TipoAtendimento tipoAtendimento, EspecialidadeMedica especialidade) {  

 

        this.nome = nome;  

 

        this.senha = senha;  

 

        this.tipoAtendimento = tipoAtendimento;  

 

        this.especialidade = especialidade;  

 

    }  

 

  

 

    public String getNome() {  

 

        return nome;  

 

    }  

 

  

 

    public String getSenha() {  

 

        return senha;  

 

    }  

 

  

 

    public TipoAtendimento getTipoAtendimento() {  

 

        return tipoAtendimento;  

 

    }  

 

  

 

    public EspecialidadeMedica getEspecialidade() {  

 

        return especialidade;  

 

    }  

 

}  

 

  

 

enum TipoAtendimento {  

 

    NORMAL, PREFERENCIAL  

 

}  

 

  

 

enum EspecialidadeMedica {  

 

    CLINICO_GERAL("Sala 1"),  

 

    CARDIOLOGISTA("Sala 2"),  

 

    DERMATOLOGISTA("Sala 3"),  

 

    GINECOLOGISTA("Sala 4"),  

 

    PEDIATRA("Sala 5");  

 

  

 

    private String sala;  

 

  

 

    EspecialidadeMedica(String sala) {  

 

        this.sala = sala;  

 

    }  

 

  

 

    public String getSala() {  

 

        return sala;  

 

    }  

 

}  

 

  

 

class FilaHospitalView {  

 

    public void exibirMensagem(String mensagem) {  

 

        JOptionPane.showMessageDialog(null, mensagem);  

 

    }  

 

}  

 

  

 

class FilaHospitalController {  

 

    private Queue<Paciente> fila;  

 

    private List<Paciente> historicoAtendimento;  

 

    private FilaHospitalView view;  

 

    private int pacientesComunsAtendidos;  

 

  

 

    public FilaHospitalController() {  

 

        fila = new LinkedList<>();  

 

        historicoAtendimento = new ArrayList<>();  

 

        view = new FilaHospitalView();  

 

        pacientesComunsAtendidos = 0;  

 

    }  

 

  

 

    public void iniciarAtendimento() {  

 

        view.exibirMensagem("Bem-vindo(a) à fila de atendimento do Hospital!");  

 

  

 

        while (true) {  

 

            int opcao = exibirMenu();  

 

  

 

            switch (opcao) {  

 

                case 1:  

 

                    String nome = JOptionPane.showInputDialog(null, "Informe o nome do paciente:");  

 

                    String senha = gerarSenhaAleatoria();  

 

                    TipoAtendimento tipoAtendimento = verificarTipoAtendimento();  

 

                    EspecialidadeMedica especialidade = selecionarEspecialidade();  

 

                    adicionarPaciente(nome, senha, tipoAtendimento, especialidade);  

 

                    break;  

 

                case 2:  

 

                    atenderProximoPaciente();  

 

                    break;  

 

                case 3:  

 

                    exibirHistoricoAtendimento();  

 

                    break;  

 

                case 4:  

 

                    return;  

 

                default:  

 

                    view.exibirMensagem("Opção inválida! Tente novamente.");  

 

            }  

 

        }  

 

    }  

 

  

 

    private int exibirMenu() {  

 

        String[] opcoes = {"Adicionar paciente", "Atender próximo paciente", "Exibir histórico de atendimento", "Sair"};  

 

        int opcaoSelecionada = JOptionPane.showOptionDialog(  

 

                null,  

 

                "Escolha uma opção:",  

 

                "Menu",  

 

                JOptionPane.DEFAULT_OPTION,  

 

                JOptionPane.PLAIN_MESSAGE,  

 

                null,  

 

                opcoes,  

 

                opcoes[0]  

 

        );  

 

  

 

        return opcaoSelecionada + 1;  

 

    }  

 

  

 

    private TipoAtendimento verificarTipoAtendimento() {  

 

        int resposta = JOptionPane.showOptionDialog(  

 

                null,  

 

                "Selecione o tipo de atendimento:",  

 

                "Tipo de Atendimento",  

 

                JOptionPane.DEFAULT_OPTION,  

 

                JOptionPane.PLAIN_MESSAGE,  

 

                null,  

 

                new String[]{"Normal", "Preferencial"},  

 

                "Normal"  

 

        );  

 

  

 

        return (resposta == 1) ? TipoAtendimento.PREFERENCIAL : TipoAtendimento.NORMAL;  

 

    }  

 

  

 

    private String gerarSenhaAleatoria() {  

 

        return Integer.toString((int) (Math.random() * 10000));  

 

    }  

 

  

 

    private EspecialidadeMedica selecionarEspecialidade() {  

 

        int index = JOptionPane.showOptionDialog(  

 

                null,  

 

                "Selecione a especialidade médica:",  

 

                "Especialidade Médica",  

 

                JOptionPane.DEFAULT_OPTION,  

 

                JOptionPane.PLAIN_MESSAGE,  

 

                null,  

 

                EspecialidadeMedica.values(),  

 

                EspecialidadeMedica.CLINICO_GERAL  

 

        );  

 

  

 

        return EspecialidadeMedica.values()[index];  

 

    }  

 

  

 

    private void adicionarPaciente(String nome, String senha, TipoAtendimento tipoAtendimento, EspecialidadeMedica especialidade) {  

 

        Paciente paciente = new Paciente(nome, senha, tipoAtendimento, especialidade);  

 

        fila.add(paciente);  

 

        view.exibirMensagem("Paciente " + nome + " adicionado(a) à fila. Senha: " + senha);  

 

        exibirFila();  

 

    }  

 

  

 

    private void atenderProximoPaciente() {  

 

        if (fila.isEmpty()) {  

 

            view.exibirMensagem("A fila está vazia.");  

 

            return;  

 

        }  

 

  

 

        Paciente paciente = fila.poll();  

 

        historicoAtendimento.add(paciente);  

 

        pacientesComunsAtendidos++;  

 

  

 

        String mensagem = "Nome: " + paciente.getNome() + "\n";  

 

        mensagem += "Senha: " + paciente.getSenha() + "\n";  

 

        mensagem += "Sala: " + paciente.getEspecialidade().getSala() + "\n";  

 

  

 

        view.exibirMensagem(mensagem);  

 

  

 

        if (paciente.getTipoAtendimento() == TipoAtendimento.NORMAL && pacientesComunsAtendidos % 3 == 0) {  

 

            Paciente pacientePreferencial = obterProximoPacientePreferencial();  

 

            if (pacientePreferencial != null) {  

 

                historicoAtendimento.add(pacientePreferencial);  

 

  

 

                String mensagemPreferencial = "Atendendo paciente preferencial:\n";  

 

                mensagemPreferencial += "Nome: " + pacientePreferencial.getNome() + "\n";  

 

                mensagemPreferencial += "Senha: " + pacientePreferencial.getSenha() + "\n";  

 

                mensagemPreferencial += "Sala: " + pacientePreferencial.getEspecialidade().getSala() + "\n";  

 

  

 

                view.exibirMensagem(mensagemPreferencial);  

 

            }  

 

        }  

 

  

 

        exibirFila();  

 

    }  

 

  

 

    private Paciente obterProximoPacientePreferencial() {  

 

        for (Paciente paciente : fila) {  

 

            if (paciente.getTipoAtendimento() == TipoAtendimento.PREFERENCIAL) {  

 

                fila.remove(paciente);  

 

                return paciente;  

 

            }  

 

        }  

 

  

 

        return null;  

 

    }  

 

  

 

    private void exibirFila() {  

 

        StringBuilder mensagem = new StringBuilder("Fila de Atendimento:\n");  

 

  

 

        for (Paciente paciente : fila) {  

 

            mensagem.append("Nome: ").append(paciente.getNome()).append(" - Tipo: ").append(paciente.getTipoAtendimento());  

 

            mensagem.append(" - Sala: ").append(paciente.getEspecialidade().getSala()).append("\n");  

 

        }  

 

  

 

        view.exibirMensagem(mensagem.toString());  

 

    }  

 

  

 

    private void exibirHistoricoAtendimento() {  

 

        if (historicoAtendimento.isEmpty()) {  

 

            view.exibirMensagem("Nenhum paciente foi atendido ainda.");  

 

            return;  

 

        }  

 

  

 

        StringBuilder mensagem = new StringBuilder("Histórico de Atendimento:\n");  

 

  

 

        for (Paciente paciente : historicoAtendimento) {  

 

            mensagem.append("Nome: ").append(paciente.getNome()).append(" - Tipo: ").append(paciente.getTipoAtendimento());  

 

            mensagem.append(" - Sala: ").append(paciente.getEspecialidade().getSala()).append("\n");  

 

        }  

 

  

 

        view.exibirMensagem(mensagem.toString());  

 

    }  

 

}  