import java.awt.List;

public class Paciente {
    private String nome;
    private String senha;
    private TipoConsulta tipoConsulta;
    private Especialidades especialidade;

    // Construtor, getters e setters omitidos para brevidade
}

enum Especialidades {
    CLINICO_GERAL("Sala 116"),
    CARDIOLOGISTA("Sala 117"),
    DERMATOLOGISTA("Sala 118"),
    OFTALMOLOGISTA("Sala 119");

    private String sala;

    Especialidades(String sala) {
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }
}

enum TipoConsulta {
    NORMAL, PREFERENCIAL
}

class FilaHospitalModel {
    private Queue<Paciente> fila;
    private List<Paciente> historicoAtendimentos;
    private int pacientesNormaisAtendidos;

    public FilaHospitalModel() {
        fila = new LinkedList<>();
        historicoAtendimentos = new ArrayList<>();
        pacientesNormaisAtendidos = 0;
    }

    public void adicionarPaciente(Paciente paciente) {
        fila.add(paciente);
    }

    public Paciente atenderProximoPaciente() {
        if (fila.isEmpty()) {
            return null;
        }

        Paciente paciente = fila.poll();
        historicoAtendimentos.add(paciente);
        pacientesNormaisAtendidos++;

        if (paciente.getTipoConsulta() == TipoConsulta.NORMAL && pacientesNormaisAtendidos % 3 == 0) {
            Paciente pacientePreferencial = obterProximoPacientePreferencial();

            if (pacientePreferencial != null) {
                historicoAtendimentos.add(pacientePreferencial);
                return pacientePreferencial;
            }
        }

        return paciente;
    }

    public List<Paciente> getHistoricoAtendimentos() {
        return historicoAtendimentos;
    }

    public List<Paciente> getFila() {
        return new ArrayList<>(fila);
    }

    private Paciente obterProximoPacientePreferencial() {
        for (Paciente paciente : fila) {
            if (paciente.getTipoConsulta() == TipoConsulta.PREFERENCIAL) {
                fila.remove(paciente);
                return paciente;
            }
        }

        return null;
    }
}
