/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.elab.hg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Mercurial",
        id = "pro.elab.hg.featureFinishAction"
)
@ActionRegistration(
        iconBase = "pro/elab/hg/ff.png",
        displayName = "#CTL_featureFinishAction"
)
@ActionReference(path = "Toolbars/Mercurial", position = 1)
@Messages("CTL_featureFinishAction=Finish feature")
public final class featureFinishAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        currentProjectInfo cpi = new currentProjectInfo();
        if (cpi.getName() != null) {
            hgFlow hf = new hgFlow(cpi.getDir());
            hf.featureFinish();
        }
    }

}
