/**
 * 
 */
package com.google.gwt.chrome.crx.linker.emiter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gwt.chrome.crx.client.GwtContentScript;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPackage;
import com.google.gwt.dev.cfg.ModuleDef;

/**
 * @author webdizz
 * 
 */
public class GwtContentScriptEmiterTest {

	@Mock
	private TreeLogger logger;

	@Mock
	private GeneratorContext context;

	@Mock
	private JClassType userType;

	@Mock
	private ModuleDefinitionLoader definitionLoader;

	@Mock
	private GwtContentScript.ManifestInfo spec;

	@Mock
	private ModuleDef moduleDef;

	private static final String MODULE_NAME = "com.gwt.contentscript.GwtContentScriptModule";

	@Before
	public void setUp() throws UnableToCompleteException {
		MockitoAnnotations.initMocks(this);

		when(userType.getSimpleSourceName()).thenReturn(Emiter.GWT_CONTENTSCRIPT_USER_TYPE);
		when(userType.getQualifiedSourceName()).thenReturn(Emiter.GWT_CONTENTSCRIPT_USER_TYPE);
		JPackage jpackage = mock(JPackage.class);
		when(userType.getPackage()).thenReturn(jpackage);
		when(userType.getAnnotation(GwtContentScript.ManifestInfo.class)).thenReturn(spec);
		when(spec.module()).thenReturn(MODULE_NAME);
		when(definitionLoader.loadModule(logger, MODULE_NAME)).thenReturn(moduleDef);
	}

	@Test(expected = UnableToCompleteException.class)
	public void shouldThrowUnableToCompleteExceptionArtifactWithoutManifestAnnotation()
			throws UnableToCompleteException {
		when(userType.getAnnotation(GwtContentScript.ManifestInfo.class)).thenReturn(null);

		invokeCodeEmition();
	}

	@Test(expected = UnableToCompleteException.class)
	public void shouldThrowUnableToCompleteExceptionIfModuleIsEmpty() throws UnableToCompleteException {
		when(spec.module()).thenReturn("");

		invokeCodeEmition();
	}

	@Test(expected = UnableToCompleteException.class)
	public void shouldThrowUnableToCompleteExceptionIfModuleWasNotFound() throws UnableToCompleteException {
		when(spec.module()).thenReturn(MODULE_NAME + "wrong_path");

		invokeCodeEmition();
	}

	@Test(expected = UnableToCompleteException.class)
	public void shouldThrowUnableToCompleteExceptionIfModuleWasNotLoaded() throws UnableToCompleteException {
		when(definitionLoader.loadModule(logger, MODULE_NAME)).thenReturn(null);

		invokeCodeEmition();
	}

	@Test(expected = UnableToCompleteException.class)
	public void shouldThrowUnableToCompleteExceptionIfGeneratedFileCannotBeFined() throws UnableToCompleteException {
		invokeCodeEmition();
	}

	protected void invokeCodeEmition() throws UnableToCompleteException {
		Emiter emiter = new GwtContentScriptEmiter(definitionLoader);
		emiter.emit(logger, context, userType, Emiter.GWT_CONTENTSCRIPT_USER_TYPE);
	}
}
