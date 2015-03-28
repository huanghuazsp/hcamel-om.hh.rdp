package com.hh.rdp.dm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.FileEditorInput;

import com.hh.rdp.dm.model.Column;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.AppUtil;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.FrameMessage;
import com.hh.rdp.util.StaticVar;
import com.hh.rdp.util.WidgetUtil;
import com.hh.rdp.util.image.ImageCache;
import com.hh.rdp.util.image.ImageKeys;

public class CreateSourceDialog extends Dialog {
	private PageGrid page;
	private Text basePackageText;
	private Text beanPackageText;
	private Text servicePackageText;
	private Text actionPackageText;
	private Text listPagePackageText;
	private Text editPagePackageText;
	private Text doMaimClassNameText;

	private Table table;

	public CreateSourceDialog(PageGrid page, Shell parentShell) {
		super(parentShell);
		this.page = page;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		table = (Table) page.getViewer().getTree().getItem(0).getData();
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		getShell().setText("一键生成代码");
		getShell().setImage(ImageCache.getImage(ImageKeys.database_lightning));
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		composite.setLayout(gridLayout);
		GridData baseGridData = new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1);
		WidgetUtil.createLabel2(composite, "BasePackage：");
		basePackageText = new Text(composite, SWT.BORDER);
		basePackageText.setLayoutData(baseGridData);

		basePackageText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				String text = ((Text) arg0.getSource()).getText();
				beanPackageText.setText(text + ".bean");
				servicePackageText.setText(text + ".service.impl");
				actionPackageText.setText(text + ".action");
				listPagePackageText.setText("jsp." + text
						+ doMaimClassNameText.getText().toLowerCase());
				editPagePackageText.setText("jsp." + text
						+ doMaimClassNameText.getText().toLowerCase());
			}
		});

		WidgetUtil.createLabel2(composite, "beanPackage：");
		beanPackageText = new Text(composite, SWT.BORDER);
		beanPackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel2(composite, "servicePackage：");
		servicePackageText = new Text(composite, SWT.BORDER);
		servicePackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel2(composite, "ServiceRestPackage：");
		actionPackageText = new Text(composite, SWT.BORDER);
		actionPackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel2(composite, "ListPagePackage：");
		listPagePackageText = new Text(composite, SWT.BORDER);
		listPagePackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel2(composite, "EditPagePackage：");
		editPagePackageText = new Text(composite, SWT.BORDER);
		editPagePackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel2(composite, "BEAN类名：");
		doMaimClassNameText = new Text(composite, SWT.BORDER);
		doMaimClassNameText.setLayoutData(baseGridData);
		doMaimClassNameText.setText(AppUtil
				.dataBaseNameToClassName(table.getName()));

		FileEditorInput fileEditorInput = (FileEditorInput) page
				.getEditorPartMain().getEditorInput();
		IFile file = fileEditorInput.getFile();
		String fileStr = file.getFullPath().toString();
		String packageName = fileStr.substring(
				fileStr.indexOf(StaticVar.JAVA_SOURCE_FOLDER)
						+ StaticVar.JAVA_SOURCE_FOLDER.length() + 1).replace(
				page.getEditorPartMain().getSourceXmlEditorPart().getTitle(),
				"");
		packageName = packageName.substring(0, packageName.length() - 1);
		int index = findCharIndex(packageName, "/", 3);
		if (index != -1) {
			packageName = packageName.substring(0, index);
		}

		basePackageText.setText(packageName.replaceAll("/", "."));

		return composite;
	}

	private int findCharIndex(String name, String cr, int index) {
		int a = 0;
		for (int i = 0; i < name.length(); i++) {
			String charStr = name.substring(i, i + 1);
			if (charStr.equals(cr)) {
				a++;
			}
			if (a == index) {
				return i;
			}
		}
		return -1;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(540, 330);
	}

	@Override
	protected void okPressed() {
		String messageStr = "";
		List<Column> columnList = table.getChildren();
		for (Column column : columnList) {
			if (Check.isEmpty(column.getName())) {
				messageStr += "（" + column.getText() + "）名称不能为空!!\n";
			}
		}
		if (Check.isNoEmpty(messageStr)) {
			FrameMessage.info(messageStr);
			return;
		}
		IProject project = AppUtil.getProject(page.getEditorPartMain());
		IJavaProject javaPoject = JavaCore.create(project);
		IPackageFragmentRoot packageFragmentRoot;
		try {
			packageFragmentRoot = javaPoject.findPackageFragmentRoot(new Path(
					"/" + javaPoject.getElementName() + "/"
							+ StaticVar.JAVA_SOURCE_FOLDER));
			createBeanCode(packageFragmentRoot);
			packageFragmentRoot = javaPoject.findPackageFragmentRoot(new Path(
					"/" + javaPoject.getElementName() + "/"
							+ StaticVar.JSP_PAGE_SOURCE_FOLDER));
			createPageList(packageFragmentRoot);
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		this.close();
	}


	public boolean checkName(String name) {
		boolean checkResult = true;
		if (Check.isEmpty(name)) {
			FrameMessage.info("字段名不能为空！");
			checkResult = false;
		}
		return checkResult;
	}
}