/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.elab.hg;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.openide.util.Exceptions;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author eugine
 */
public class hgFlow {

    private String projectDir = null;

    hgFlow(String dir) {
        projectDir = dir;
    }

    /**
     * Check if the project supports mercurial with flow
     *
     * @return
     */
    public boolean isFlowProject() {
        File f = new File(projectDir + "/.hgflow");
        return f.exists();
    }

    /**
     *
     * @param label
     */
    public void featureStart(String label) {
        if (isFlowProject()) {
            try {
                InputStream rtm = Runtime.getRuntime().exec("hg checkout develop", null, new File(projectDir)).getInputStream();
                output(rtm);
                rtm = Runtime.getRuntime().exec("hg flow feature start " + label, null, new File(projectDir)).getInputStream();
                output(rtm);
            } catch (Exception e) {
            }
        }
    }

    /**
     *
     * @param label
     */
    public void releaseStart(String label) {
        if (isFlowProject()) {
            try {
                InputStream rtm = Runtime.getRuntime().exec("hg checkout develop", null, new File(projectDir)).getInputStream();
                output(rtm);
                rtm = Runtime.getRuntime().exec("hg flow release start " + label, null, new File(projectDir)).getInputStream();
                output(rtm);
            } catch (Exception e) {
            }
        }
    }

    /**
     *
     */
    public void featureFinish() {
        if (isFlowProject()) {
            try {
                InputStream rtm = Runtime.getRuntime().exec("hg flow feature publish", null, new File(projectDir)).getInputStream();
                output(rtm);
                rtm = Runtime.getRuntime().exec("hg flow feature finish", null, new File(projectDir)).getInputStream();
                output(rtm);
            } catch (Exception e) {
            }
        }
    }

    /**
     *
     */
    public void releaseFinish() {
        if (isFlowProject()) {
            try {
                InputStream rtm = Runtime.getRuntime().exec("hg flow release publish", null, new File(projectDir)).getInputStream();
                output(rtm);
                rtm = Runtime.getRuntime().exec("hg flow release finish", null, new File(projectDir)).getInputStream();
                output(rtm);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Output into the Netbean's Output window
     *
     * @param is
     */
    private void output(InputStream is) {
        InputStreamReader isr = null;
        try {
            InputOutput io = IOProvider.getDefault().getIO("Mercurial", true);
            isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            io.select();
            String line = "";
            while ((line = br.readLine()) != null) {
                io.getOut().println(line);
            }
            br.close();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);

        }
    }
}
