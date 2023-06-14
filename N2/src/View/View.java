import javax.swing.JOptionPane;

class FilaHospitalView {
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public String obterNomePaciente() {
        return JOptionPane.showInputDialog(null, "Informe o nome do paciente:");
    }

    public TipoConsulta obterTipoConsulta() {
        int resposta = JOptionPane.showOptionDialog(
                null,
                "Selecione o tipo de consulta:",
                "Tipo de Consulta",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Normal", "Preferencial"},
                "Normal"
        );

        return (resposta == 1) ? TipoConsulta.PREFERENCIAL : TipoConsulta.NORMAL;
    }

    public Especialidades selecionarEspecialidade() {
        int index = JOptionPane.showOptionDialog(
                null,
                "Selecione a especialidade médica:",
                "Especialidades",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                Especialidades.values(),
                Especialidades.CLINICO_GERAL
        );

        return Especialidades.values()[index];
    }

    public void exibirFila(List<Paciente> fila) {
        StringBuilder mensagem = new StringBuilder("Fila de Atendimento:\n");

        for (Paciente paciente : fila) {
            mensagem.append("Senha: ").append(paciente.getSenha()).append(" - Sala: ").append(paciente.getEspecialidade().getSala()).append("\n");
        }

        exibirMensagem(mensagem.toString());
    }

    public void exibirHistoricoAtendimentos(List<Paciente> historicoAtendimentos) {
        if (historicoAtendimentos.isEmpty()) {
            exibirMensagem("Nenhum paciente foi atendido ainda.");
            return;
        }

        StringBuilder mensagem = new StringBuilder("Histórico de Atendimentos:\n");

        for (Paciente paciente : historicoAtendimentos) {
            mensagem.append("Nome: ").append(paciente.getNome()).append(" - Sala: ").append(paciente.getEspecialidade().getSala()).append("\n");
        }

        exibirMensagem(mensagem.toString());
    }
}
