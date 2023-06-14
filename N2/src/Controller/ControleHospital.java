public class ContFilaController {
    private FilaHospitalModel model;
    private FilaHospitalView view;

    public ContFilaController(FilaHospitalModel model, FilaHospitalView view) {
        this.model = model;
        this.view = view;
    }

    public void iniciarAtendimento() {
        view.exibirMensagem("Bem-vindo(a) � fila de atendimento!");

        while (true) {
            int opcao = exibirMenu();

            switch (opcao) {
                case 1:
                    String nome = view.obterNomePaciente();
                    TipoConsulta tipoConsulta = view.obterTipoConsulta();
                    Especialidades especialidade = view.selecionarEspecialidade();
                    Paciente paciente = new Paciente(nome, gerarSenhaAleatoria(), tipoConsulta, especialidade);
                    model.adicionarPaciente(paciente);
                    view.exibirMensagem("Paciente " + nome + " adicionado(a) � fila. Senha: " + paciente.getSenha());
                    view.exibirFila(model.getFila());
                    break;
                case 2:
                    Paciente proximoPaciente = model.atenderProximoPaciente();
                    if (proximoPaciente != null) {
                        view.exibirMensagem("Nome: " + proximoPaciente.getNome() + "\nSala: " + proximoPaciente.getEspecialidade().getSala());
                    } else {
                        view.exibirMensagem("A fila est� vazia.");
                    }
                    view.exibirFila(model.getFila());
                    break;
                case 3:
                    view.exibirHistoricoAtendimentos(model.getHistoricoAtendimentos());
                    break;
                case 4:
                    return;
                default:
                    view.exibirMensagem("Op��o inv�lida! Tente novamente.");
            }
        }
    }

    private int exibirMenu() {
        String[] opcoes = {"Adicionar paciente", "Atender pr�ximo paciente", "Exibir hist�rico de atendimentos", "Sair"};
        int opcaoSelecionada = JOptionPane.showOptionDialog(
                null,
                "Escolha uma op��o:",
                "Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        return opcaoSelecionada + 1;
    }

    private String gerarSenhaAleatoria() {
        return Integer.toString((int) (Math.random() * 10000));
    }
}
