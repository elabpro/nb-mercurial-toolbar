/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.elab.hg;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author eugine
 */
public class hgFlow {

    private String projectDir = null;

    hgFlow(String dir) {
        projectDir = dir;
    }

    public boolean isFlowProject() {
        File f = new File(projectDir + "/.hgflow");
        return f.exists();
    }

    public void featureStart(String label) {
        if (isFlowProject()) {
            try {
                Runtime.getRuntime().exec("hg checkout develop", null, new File(projectDir));
                Runtime.getRuntime().exec("hg flow feature start " + label, null, new File(projectDir));
            } catch (Exception e) {
            }
        }
    }

    public void releaseStart(String label) {
        if (isFlowProject()) {
            try {
                Runtime.getRuntime().exec("hg checkout develop", null, new File(projectDir));
                Runtime.getRuntime().exec("hg flow release start " + label, null, new File(projectDir));
            } catch (Exception e) {
            }
        }
    }

    public void featureFinish() {
        if (isFlowProject()) {
            try {
                Runtime.getRuntime().exec("hg flow feature publish", null, new File(projectDir));
                Runtime.getRuntime().exec("hg flow feature finish", null, new File(projectDir));
            } catch (Exception e) {
            }
        }
    }

    public void releaseFinish() {
        if (isFlowProject()) {
            try {
                Runtime.getRuntime().exec("hg flow release publish", null, new File(projectDir));
                Runtime.getRuntime().exec("hg flow release finish", null, new File(projectDir));
            } catch (Exception e) {
            }
        }
    }
}
