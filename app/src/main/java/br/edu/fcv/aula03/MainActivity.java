package br.edu.fcv.aula03;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificacaoBig();

    }

    private void notificacao() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("My notification");
        mBuilder.setContentText("Hello World!");

        NotificationManagerCompat mNotificationManager =
                NotificationManagerCompat.from(this);

        mNotificationManager.notify(1, mBuilder.build());
    }



    private void notificacao2(){


        NotificationCompat.Builder b = new NotificationCompat.Builder(this);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.mipmap.ic_launcher); // Ícone
        b.setContentTitle("titulo"); // Título
        b.setContentText("texto"); // Mensagem
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        b.setColor(Color.GREEN);

        // Heads-up notification
        Intent i = new Intent(getApplication(),MainActivity.class);
        PendingIntent p = getPendingIntent(this, i, 1);
        b.setFullScreenIntent(p, false);

        // Privada se estiver na tela de bloqueio
        b.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);


// Ação customizada (deixei a mesma intent para os dois)
        PendingIntent actionIntent = PendingIntent.getBroadcast(
                this, 0, new Intent(ACTION_VISUALIZAR), 0);
        b.addAction(R.drawable.ic_acao_pause, "Pause", actionIntent);
        b.addAction(R.drawable.ic_acao_play, "Play", actionIntent);



        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(1, b.build());

    }
    private static PendingIntent getPendingIntent(Context context, Intent intent, int id) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(intent.getComponent());
        stackBuilder.addNextIntent(intent);
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }

    private void notificacaoBig(){
        List<String> lines = new ArrayList<>();
        lines.add("Mensagem 1");
        lines.add("Mensagem 2");
        lines.add("Mensagem 3");
        // Configura o estilo Inbox
        int size = lines.size();
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("titulo");
        for (String s: lines) {
            inboxStyle.addLine(s);
        }
        inboxStyle.setSummaryText("texto");

// Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(this);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.mipmap.ic_launcher); // Ícone
        b.setContentTitle("titulo"); // Título
        b.setContentText("texto"); // Mensagem
        //b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela
        b.setNumber(size); // Número para aparecer na notificação
        b.setStyle(inboxStyle); // Estilo customizado

        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(1, b.build());

    }


}
