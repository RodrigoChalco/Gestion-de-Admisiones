package servicios;
public class NotificadorPostulante {

    private final EmailSender emailSender = new EmailSender();

    public void notificar(String correoPostulante, String nuevoEstado) {
        String asunto = "Resultado de tu postulación";
        String cuerpo;

        switch (nuevoEstado.toLowerCase()) {
            case "aprobado":
                cuerpo = "¡Felicidades! Tu postulación ha sido aprobada. Avanzas al siguiente paso.";
                break;
            case "rechazado":
                cuerpo = "Tu postulación ha sido rechazada. Revisa las observaciones y vuelve a intentar.";
                break;
            case "pendiente":
                cuerpo = "Tu postulación sigue en revisión. Te notificaremos pronto.";
                break;
            default:
                cuerpo = "Se ha actualizado el estado de tu postulación.";
        }

        emailSender.enviarCorreo(correoPostulante, asunto, cuerpo);
    }
}
