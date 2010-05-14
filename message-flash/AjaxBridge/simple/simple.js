/**
 * WARNING! THIS IS A GENERATED FILE, AND WILL BE RE-GENERATED EACH TIME THE
 * AJAXBRIDGE IS RUN.
 *
 * You should keep your javascript code inside this file as light as possible, 
 * and rather keep the body of your Ajax application in separate *.js files. 
 *
 * Do make a backup of your changes, before re-generating this file (AjaxBridge 
 * will display a warning message to you).
 *
 * Please refer to the built-in documentation inside the AjaxBridge application 
 * for help on using this file.
 */
 
 
/**
 * Application "simple.mxml"
 */

/**
 * The "simple" javascript namespace. All the functions/variables you
 * have selected under the "simple.mxml" in the tree will be
 * available as static members of this namespace object.
 */
simple = {};


/**
 * Listen for the instantiation of the Flex application over the bridge
 */
FABridge.addInitializationCallback("b_simple", simpleReady);


/**
 * Hook here all the code that must run as soon as the "simple" class
 * finishes its instantiation over the bridge.
 *
 * For basic tasks, such as running a Flex method on the click of a javascript
 * button, chances are that both Ajax and Flex may well have loaded before the 
 * user actually clicks the button.
 *
 * However, using the "simpleReady()" is the safest way, as it will 
 * let Ajax know that involved Flex classes are available for use.
 */
function simpleReady() {

	// Initialize the "root" object. This represents the actual 
	// "simple.mxml" flex application.
	b_simple_root = FABridge["b_simple"].root();
	

	// Global variables in the "simple.mxml" application (converted 
	// to getters and setters)

	simple.getControlBar = function () {
		return b_simple_root.getControlBar();
	};


	simple.getPageTitle = function () {
		return b_simple_root.getPageTitle();
	};


	simple.getPreloader = function () {
		return b_simple_root.getPreloader();
	};


	simple.getScriptTimeLimit = function () {
		return b_simple_root.getScriptTimeLimit();
	};


	simple.getResetHistory = function () {
		return b_simple_root.getResetHistory();
	};


	simple.getFrameRate = function () {
		return b_simple_root.getFrameRate();
	};


	simple.getScriptRecursionLimit = function () {
		return b_simple_root.getScriptRecursionLimit();
	};


	simple.getHistoryManagementEnabled = function () {
		return b_simple_root.getHistoryManagementEnabled();
	};


	simple.getUsePreloader = function () {
		return b_simple_root.getUsePreloader();
	};


	simple.getTransitions = function () {
		return b_simple_root.getTransitions();
	};


	simple.getStates = function () {
		return b_simple_root.getStates();
	};


	simple.getConstructor = function () {
		return b_simple_root.getConstructor();
	};


	simple.getSuper = function () {
		return b_simple_root.getSuper();
	};


	simple.getThis = function () {
		return b_simple_root.getThis();
	};


	// Global functions in the "simple.mxml" application

	simple.ping = function() {
		b_simple_root.ping();
	};

	simple.connect = function() {
		b_simple_root.connect();
	};

	simple.onClose = function(argEvent) {
		b_simple_root.onClose(argEvent);
	};

	simple.onError = function(argIOErrorEvent) {
		b_simple_root.onError(argIOErrorEvent);
	};

	simple.send = function(argString) {
		b_simple_root.send(argString);
	};

	simple.onConnect = function(argEvent) {
		b_simple_root.onConnect(argEvent);
	};

	simple.close = function() {
		b_simple_root.close();
	};

	simple.onData = function(argProgressEvent) {
		b_simple_root.onData(argProgressEvent);
	};

	simple.onSecurityError = function(argSecurityErrorEvent) {
		b_simple_root.onSecurityError(argSecurityErrorEvent);
	};

	simple.begin = function() {
		b_simple_root.begin();
	};

	simple.reset = function() {
		b_simple_root.reset();
	};

	simple.finishPrint = function(argObject, argIFlexDisplayObject) {
		b_simple_root.finishPrint(argObject, argIFlexDisplayObject);
	};

	simple.setPercentWidth = function(argNumber) {
		b_simple_root.setPercentWidth(argNumber);
	};

	simple.getUrl = function() {
		return b_simple_root.getUrl();
	};

	simple.initialize = function() {
		b_simple_root.initialize();
	};

	simple.styleChanged = function(argString) {
		b_simple_root.styleChanged(argString);
	};

	simple.getViewSourceURL = function() {
		return b_simple_root.getViewSourceURL();
	};

	simple.setViewSourceURL = function(argString) {
		b_simple_root.setViewSourceURL(argString);
	};

	simple.setIcon = function(argClass) {
		b_simple_root.setIcon(argClass);
	};

	simple.setTabIndex = function(argInt) {
		b_simple_root.setTabIndex(argInt);
	};

	simple.setToolTip = function(argString) {
		b_simple_root.setToolTip(argString);
	};

	simple.getViewMetrics = function() {
		return b_simple_root.getViewMetrics();
	};

	simple.prepareToPrint = function(argIFlexDisplayObject) {
		return b_simple_root.prepareToPrint(argIFlexDisplayObject);
	};

	simple.getId = function() {
		return b_simple_root.getId();
	};

	simple.Application = function() {
		return b_simple_root.Application();
	};

	simple.setPercentHeight = function(argNumber) {
		b_simple_root.setPercentHeight(argNumber);
	};

	simple.setLabel = function(argString) {
		b_simple_root.setLabel(argString);
	};

	simple.getParameters = function() {
		return b_simple_root.getParameters();
	};

	simple.getChildIndex = function(argDisplayObject) {
		return b_simple_root.getChildIndex(argDisplayObject);
	};

	simple.addToCreationQueue = function(argObject, argInt, argFunction, argIFlexDisplayObject) {
		b_simple_root.addToCreationQueue(argObject, argInt, argFunction, argIFlexDisplayObject);
	};

	simple.getChildIndex = function(argDisplayObject) {
		return b_simple_root.getChildIndex(argDisplayObject);
	};

	simple.getChildByName = function(argString) {
		return b_simple_root.getChildByName(argString);
	};

	simple.getNumChildren = function() {
		return b_simple_root.getNumChildren();
	};

	simple.setChildIndex = function(argDisplayObject, argInt) {
		b_simple_root.setChildIndex(argDisplayObject, argInt);
	};

	simple.getTabChildren = function() {
		return b_simple_root.getTabChildren();
	};

	simple.setTabChildren = function(argBoolean) {
		b_simple_root.setTabChildren(argBoolean);
	};

	simple.addChild = function(argDisplayObject) {
		return b_simple_root.addChild(argDisplayObject);
	};

	simple.swapChildren = function(argDisplayObject1, argDisplayObject2) {
		b_simple_root.swapChildren(argDisplayObject1, argDisplayObject2);
	};

	simple.removeChild = function(argDisplayObject) {
		return b_simple_root.removeChild(argDisplayObject);
	};

	simple.contains = function(argDisplayObject) {
		return b_simple_root.contains(argDisplayObject);
	};

	simple.removeChildAt = function(argInt) {
		return b_simple_root.removeChildAt(argInt);
	};

	simple.getTextSnapshot = function() {
		return b_simple_root.getTextSnapshot();
	};

	simple.swapChildrenAt = function(argInt1, argInt2) {
		b_simple_root.swapChildrenAt(argInt1, argInt2);
	};

	simple.getMouseChildren = function() {
		return b_simple_root.getMouseChildren();
	};

	simple.setMouseChildren = function(argBoolean) {
		b_simple_root.setMouseChildren(argBoolean);
	};

	simple.areInaccessibleObjectsUnderPoint = function(argPoint) {
		return b_simple_root.areInaccessibleObjectsUnderPoint(argPoint);
	};

	simple.DisplayObjectContainer = function() {
		return b_simple_root.DisplayObjectContainer();
	};

	simple.getChildAt = function(argInt) {
		return b_simple_root.getChildAt(argInt);
	};

	simple.getObjectsUnderPoint = function(argPoint) {
		return b_simple_root.getObjectsUnderPoint(argPoint);
	};

	simple.addChildAt = function(argDisplayObject, argInt) {
		return b_simple_root.addChildAt(argDisplayObject, argInt);
	};

	simple.regenerateStyleCache = function(argBoolean) {
		b_simple_root.regenerateStyleCache(argBoolean);
	};

	simple.initialize = function() {
		b_simple_root.initialize();
	};

	simple.getAutomationTabularData = function() {
		return b_simple_root.getAutomationTabularData();
	};

	simple.getUid = function() {
		return b_simple_root.getUid();
	};

	simple.setUid = function(argString) {
		b_simple_root.setUid(argString);
	};

	simple.getScaleY = function() {
		return b_simple_root.getScaleY();
	};

	simple.setScaleY = function(argNumber) {
		b_simple_root.setScaleY(argNumber);
	};

	simple.getScaleX = function() {
		return b_simple_root.getScaleX();
	};

	simple.setScaleX = function(argNumber) {
		b_simple_root.setScaleX(argNumber);
	};

	simple.getRepeaterItem = function(argInt) {
		return b_simple_root.getRepeaterItem(argInt);
	};

	simple.getStyleDeclaration = function() {
		return b_simple_root.getStyleDeclaration();
	};

	simple.setStyleDeclaration = function(argCSSStyleDeclaration) {
		b_simple_root.setStyleDeclaration(argCSSStyleDeclaration);
	};

	simple.getMaxWidth = function() {
		return b_simple_root.getMaxWidth();
	};

	simple.setMaxWidth = function(argNumber) {
		b_simple_root.setMaxWidth(argNumber);
	};

	simple.measureHTMLText = function(argString) {
		return b_simple_root.measureHTMLText(argString);
	};

	simple.getSystemManager = function() {
		return b_simple_root.getSystemManager();
	};

	simple.setSystemManager = function(argISystemManager) {
		b_simple_root.setSystemManager(argISystemManager);
	};

	simple.validateDisplayList = function() {
		b_simple_root.validateDisplayList();
	};

	simple.getMinWidth = function() {
		return b_simple_root.getMinWidth();
	};

	simple.setMinWidth = function(argNumber) {
		b_simple_root.setMinWidth(argNumber);
	};

	simple.getExplicitOrMeasuredWidth = function() {
		return b_simple_root.getExplicitOrMeasuredWidth();
	};

	simple.getInitialized = function() {
		return b_simple_root.getInitialized();
	};

	simple.setInitialized = function(argBoolean) {
		b_simple_root.setInitialized(argBoolean);
	};

	simple.contentToGlobal = function(argPoint) {
		return b_simple_root.contentToGlobal(argPoint);
	};

	simple.getAutomationValue = function() {
		return b_simple_root.getAutomationValue();
	};

	simple.getExplicitHeight = function() {
		return b_simple_root.getExplicitHeight();
	};

	simple.setExplicitHeight = function(argNumber) {
		b_simple_root.setExplicitHeight(argNumber);
	};

	simple.executeBindings = function(argBoolean) {
		b_simple_root.executeBindings(argBoolean);
	};

	simple.getPercentWidth = function() {
		return b_simple_root.getPercentWidth();
	};

	simple.setPercentWidth = function(argNumber) {
		b_simple_root.setPercentWidth(argNumber);
	};

	simple.getModuleFactory = function() {
		return b_simple_root.getModuleFactory();
	};

	simple.setModuleFactory = function(argIFlexModuleFactory) {
		b_simple_root.setModuleFactory(argIFlexModuleFactory);
	};

	simple.getParentApplication = function() {
		return b_simple_root.getParentApplication();
	};

	simple.drawRoundRect = function(argNumber1, argNumber2, argNumber3, argNumber4, argObject5, argObject6, argObject7, argObject8, argString, argArray, argObject9) {
		b_simple_root.drawRoundRect(argNumber1, argNumber2, argNumber3, argNumber4, argObject5, argObject6, argObject7, argObject8, argString, argArray, argObject9);
	};

	simple.resolveAutomationIDPart = function(argObject) {
		return b_simple_root.resolveAutomationIDPart(argObject);
	};

	simple.setChildIndex = function(argDisplayObject, argInt) {
		b_simple_root.setChildIndex(argDisplayObject, argInt);
	};

	simple.getUpdateCompletePendingFlag = function() {
		return b_simple_root.getUpdateCompletePendingFlag();
	};

	simple.setUpdateCompletePendingFlag = function(argBoolean) {
		b_simple_root.setUpdateCompletePendingFlag(argBoolean);
	};

	simple.getProcessedDescriptors = function() {
		return b_simple_root.getProcessedDescriptors();
	};

	simple.setProcessedDescriptors = function(argBoolean) {
		b_simple_root.setProcessedDescriptors(argBoolean);
	};

	simple.getDoubleClickEnabled = function() {
		return b_simple_root.getDoubleClickEnabled();
	};

	simple.setDoubleClickEnabled = function(argBoolean) {
		b_simple_root.setDoubleClickEnabled(argBoolean);
	};

	simple.setActualSize = function(argNumber1, argNumber2) {
		b_simple_root.setActualSize(argNumber1, argNumber2);
	};

	simple.getOwner = function() {
		return b_simple_root.getOwner();
	};

	simple.setOwner = function(argDisplayObjectContainer) {
		b_simple_root.setOwner(argDisplayObjectContainer);
	};

	simple.measureText = function(argString) {
		return b_simple_root.measureText(argString);
	};

	simple.getRepeaters = function() {
		return b_simple_root.getRepeaters();
	};

	simple.setRepeaters = function(argArray) {
		b_simple_root.setRepeaters(argArray);
	};

	simple.notifyStyleChangeInChildren = function(argString, argBoolean) {
		b_simple_root.notifyStyleChangeInChildren(argString, argBoolean);
	};

	simple.setStyle = function(argString, argObject) {
		b_simple_root.setStyle(argString, argObject);
	};

	simple.getFlexContextMenu = function() {
		return b_simple_root.getFlexContextMenu();
	};

	simple.setFlexContextMenu = function(argIFlexContextMenu) {
		b_simple_root.setFlexContextMenu(argIFlexContextMenu);
	};

	simple.createReferenceOnParentDocument = function(argIFlexDisplayObject) {
		b_simple_root.createReferenceOnParentDocument(argIFlexDisplayObject);
	};

	simple.getMouseFocusEnabled = function() {
		return b_simple_root.getMouseFocusEnabled();
	};

	simple.setMouseFocusEnabled = function(argBoolean) {
		b_simple_root.setMouseFocusEnabled(argBoolean);
	};

	simple.stopDrag = function() {
		b_simple_root.stopDrag();
	};

	simple.localToContent = function(argPoint) {
		return b_simple_root.localToContent(argPoint);
	};

	simple.prepareToPrint = function(argIFlexDisplayObject) {
		return b_simple_root.prepareToPrint(argIFlexDisplayObject);
	};

	simple.endEffectsStarted = function() {
		b_simple_root.endEffectsStarted();
	};

	simple.registerEffects = function(argArray) {
		b_simple_root.registerEffects(argArray);
	};

	simple.getActiveEffects = function() {
		return b_simple_root.getActiveEffects();
	};

	simple.getFocusPane = function() {
		return b_simple_root.getFocusPane();
	};

	simple.setFocusPane = function(argSprite) {
		b_simple_root.setFocusPane(argSprite);
	};

	simple.getInheritingStyles = function() {
		return b_simple_root.getInheritingStyles();
	};

	simple.setInheritingStyles = function(argObject) {
		b_simple_root.setInheritingStyles(argObject);
	};

	simple.verticalGradientMatrix = function(argNumber1, argNumber2, argNumber3, argNumber4) {
		return b_simple_root.verticalGradientMatrix(argNumber1, argNumber2, argNumber3, argNumber4);
	};

	simple.determineTextFormatFromStyles = function() {
		return b_simple_root.determineTextFormatFromStyles();
	};

	simple.getMaxHeight = function() {
		return b_simple_root.getMaxHeight();
	};

	simple.setMaxHeight = function(argNumber) {
		b_simple_root.setMaxHeight(argNumber);
	};

	simple.getBaselinePosition = function() {
		return b_simple_root.getBaselinePosition();
	};

	simple.callLater = function(argFunction, argArray) {
		b_simple_root.callLater(argFunction, argArray);
	};

	simple.hasFontContextChanged = function() {
		return b_simple_root.hasFontContextChanged();
	};

	simple.getDescriptor = function() {
		return b_simple_root.getDescriptor();
	};

	simple.setDescriptor = function(argUIComponentDescriptor) {
		b_simple_root.setDescriptor(argUIComponentDescriptor);
	};

	simple.deleteReferenceOnParentDocument = function(argIFlexDisplayObject) {
		b_simple_root.deleteReferenceOnParentDocument(argIFlexDisplayObject);
	};

	simple.getErrorString = function() {
		return b_simple_root.getErrorString();
	};

	simple.setErrorString = function(argString) {
		b_simple_root.setErrorString(argString);
	};

	simple.getWidth = function() {
		return b_simple_root.getWidth();
	};

	simple.setWidth = function(argNumber) {
		b_simple_root.setWidth(argNumber);
	};

	simple.getInstanceIndex = function() {
		return b_simple_root.getInstanceIndex();
	};

	simple.move = function(argNumber1, argNumber2) {
		b_simple_root.move(argNumber1, argNumber2);
	};

	simple.getClassStyleDeclarations = function() {
		return b_simple_root.getClassStyleDeclarations();
	};

	simple.initializeRepeaterArrays = function(argIRepeaterClient) {
		b_simple_root.initializeRepeaterArrays(argIRepeaterClient);
	};

	simple.getExplicitMaxWidth = function() {
		return b_simple_root.getExplicitMaxWidth();
	};

	simple.setExplicitMaxWidth = function(argNumber) {
		b_simple_root.setExplicitMaxWidth(argNumber);
	};

	simple.getExplicitMinHeight = function() {
		return b_simple_root.getExplicitMinHeight();
	};

	simple.setExplicitMinHeight = function(argNumber) {
		b_simple_root.setExplicitMinHeight(argNumber);
	};

	simple.clearStyle = function(argString) {
		b_simple_root.clearStyle(argString);
	};

	simple.invalidateProperties = function() {
		b_simple_root.invalidateProperties();
	};

	simple.setCacheHeuristic = function(argBoolean) {
		b_simple_root.setCacheHeuristic(argBoolean);
	};

	simple.getFilters = function() {
		return b_simple_root.getFilters();
	};

	simple.setFilters = function(argArray) {
		b_simple_root.setFilters(argArray);
	};

	simple.validateProperties = function() {
		b_simple_root.validateProperties();
	};

	simple.getIncludeInLayout = function() {
		return b_simple_root.getIncludeInLayout();
	};

	simple.setIncludeInLayout = function(argBoolean) {
		b_simple_root.setIncludeInLayout(argBoolean);
	};

	simple.addChildAt = function(argDisplayObject, argInt) {
		return b_simple_root.addChildAt(argDisplayObject, argInt);
	};

	simple.getAutomationName = function() {
		return b_simple_root.getAutomationName();
	};

	simple.setAutomationName = function(argString) {
		b_simple_root.setAutomationName(argString);
	};

	simple.getClassName = function() {
		return b_simple_root.getClassName();
	};

	simple.getNonInheritingStyles = function() {
		return b_simple_root.getNonInheritingStyles();
	};

	simple.setNonInheritingStyles = function(argObject) {
		b_simple_root.setNonInheritingStyles(argObject);
	};

	simple.getExplicitWidth = function() {
		return b_simple_root.getExplicitWidth();
	};

	simple.setExplicitWidth = function(argNumber) {
		b_simple_root.setExplicitWidth(argNumber);
	};

	simple.getMinHeight = function() {
		return b_simple_root.getMinHeight();
	};

	simple.setMinHeight = function(argNumber) {
		b_simple_root.setMinHeight(argNumber);
	};

	simple.dispatchEvent = function(argEvent) {
		return b_simple_root.dispatchEvent(argEvent);
	};

	simple.getExplicitMinWidth = function() {
		return b_simple_root.getExplicitMinWidth();
	};

	simple.setExplicitMinWidth = function(argNumber) {
		b_simple_root.setExplicitMinWidth(argNumber);
	};

	simple.getStyle = function(argString) {
		return b_simple_root.getStyle(argString);
	};

	simple.getMouseY = function() {
		return b_simple_root.getMouseY();
	};

	simple.getMouseX = function() {
		return b_simple_root.getMouseX();
	};

	simple.getScreen = function() {
		return b_simple_root.getScreen();
	};

	simple.getExplicitOrMeasuredHeight = function() {
		return b_simple_root.getExplicitOrMeasuredHeight();
	};

	simple.setFocus = function() {
		b_simple_root.setFocus();
	};

	simple.horizontalGradientMatrix = function(argNumber1, argNumber2, argNumber3, argNumber4) {
		return b_simple_root.horizontalGradientMatrix(argNumber1, argNumber2, argNumber3, argNumber4);
	};

	simple.setConstraintValue = function(argString, argObject) {
		b_simple_root.setConstraintValue(argString, argObject);
	};

	simple.getInstanceIndices = function() {
		return b_simple_root.getInstanceIndices();
	};

	simple.setInstanceIndices = function(argArray) {
		b_simple_root.setInstanceIndices(argArray);
	};

	simple.getRepeaterIndices = function() {
		return b_simple_root.getRepeaterIndices();
	};

	simple.setRepeaterIndices = function(argArray) {
		b_simple_root.setRepeaterIndices(argArray);
	};

	simple.getTweeningProperties = function() {
		return b_simple_root.getTweeningProperties();
	};

	simple.setTweeningProperties = function(argArray) {
		b_simple_root.setTweeningProperties(argArray);
	};

	simple.getCachePolicy = function() {
		return b_simple_root.getCachePolicy();
	};

	simple.setCachePolicy = function(argString) {
		b_simple_root.setCachePolicy(argString);
	};

	simple.addChild = function(argDisplayObject) {
		return b_simple_root.addChild(argDisplayObject);
	};

	simple.invalidateSize = function() {
		b_simple_root.invalidateSize();
	};

	simple.setVisible = function(argBoolean1, argBoolean2) {
		b_simple_root.setVisible(argBoolean1, argBoolean2);
	};

	simple.parentChanged = function(argDisplayObjectContainer) {
		b_simple_root.parentChanged(argDisplayObjectContainer);
	};

	simple.getMeasuredHeight = function() {
		return b_simple_root.getMeasuredHeight();
	};

	simple.setMeasuredHeight = function(argNumber) {
		b_simple_root.setMeasuredHeight(argNumber);
	};

	simple.removeChild = function(argDisplayObject) {
		return b_simple_root.removeChild(argDisplayObject);
	};

	simple.validateNow = function() {
		b_simple_root.validateNow();
	};

	simple.invalidateDisplayList = function() {
		b_simple_root.invalidateDisplayList();
	};

	simple.getMeasuredWidth = function() {
		return b_simple_root.getMeasuredWidth();
	};

	simple.setMeasuredWidth = function(argNumber) {
		b_simple_root.setMeasuredWidth(argNumber);
	};

	simple.getAutomationChildAt = function(argInt) {
		return b_simple_root.getAutomationChildAt(argInt);
	};

	simple.getPercentHeight = function() {
		return b_simple_root.getPercentHeight();
	};

	simple.setPercentHeight = function(argNumber) {
		b_simple_root.setPercentHeight(argNumber);
	};

	simple.getIsPopUp = function() {
		return b_simple_root.getIsPopUp();
	};

	simple.setIsPopUp = function(argBoolean) {
		b_simple_root.setIsPopUp(argBoolean);
	};

	simple.getId = function() {
		return b_simple_root.getId();
	};

	simple.setId = function(argString) {
		b_simple_root.setId(argString);
	};

	simple.getStyleName = function() {
		return b_simple_root.getStyleName();
	};

	simple.setStyleName = function(argObject) {
		b_simple_root.setStyleName(argObject);
	};

	simple.globalToContent = function(argPoint) {
		return b_simple_root.globalToContent(argPoint);
	};

	simple.getIsDocument = function() {
		return b_simple_root.getIsDocument();
	};

	simple.setCacheAsBitmap = function(argBoolean) {
		b_simple_root.setCacheAsBitmap(argBoolean);
	};

	simple.getRepeaterIndex = function() {
		return b_simple_root.getRepeaterIndex();
	};

	simple.getParent = function() {
		return b_simple_root.getParent();
	};

	simple.getRepeater = function() {
		return b_simple_root.getRepeater();
	};

	simple.getMeasuredMinHeight = function() {
		return b_simple_root.getMeasuredMinHeight();
	};

	simple.setMeasuredMinHeight = function(argNumber) {
		b_simple_root.setMeasuredMinHeight(argNumber);
	};

	simple.getFocusManager = function() {
		return b_simple_root.getFocusManager();
	};

	simple.setFocusManager = function(argIFocusManager) {
		b_simple_root.setFocusManager(argIFocusManager);
	};

	simple.effectStarted = function(argIEffectInstance) {
		b_simple_root.effectStarted(argIEffectInstance);
	};

	simple.UIComponent = function() {
		return b_simple_root.UIComponent();
	};

	simple.getDocument = function() {
		return b_simple_root.getDocument();
	};

	simple.setDocument = function(argObject) {
		b_simple_root.setDocument(argObject);
	};

	simple.getFocus = function() {
		return b_simple_root.getFocus();
	};

	simple.validationResultHandler = function(argValidationResultEvent) {
		b_simple_root.validationResultHandler(argValidationResultEvent);
	};

	simple.setCurrentState = function(argString, argBoolean) {
		b_simple_root.setCurrentState(argString, argBoolean);
	};

	simple.finishPrint = function(argObject, argIFlexDisplayObject) {
		b_simple_root.finishPrint(argObject, argIFlexDisplayObject);
	};

	simple.contentToLocal = function(argPoint) {
		return b_simple_root.contentToLocal(argPoint);
	};

	simple.validateSize = function(argBoolean) {
		b_simple_root.validateSize(argBoolean);
	};

	simple.getEnabled = function() {
		return b_simple_root.getEnabled();
	};

	simple.setEnabled = function(argBoolean) {
		b_simple_root.setEnabled(argBoolean);
	};

	simple.getNestLevel = function() {
		return b_simple_root.getNestLevel();
	};

	simple.setNestLevel = function(argInt) {
		b_simple_root.setNestLevel(argInt);
	};

	simple.getCursorManager = function() {
		return b_simple_root.getCursorManager();
	};

	simple.getValidationSubField = function() {
		return b_simple_root.getValidationSubField();
	};

	simple.setValidationSubField = function(argString) {
		b_simple_root.setValidationSubField(argString);
	};

	simple.setAlpha = function(argNumber) {
		b_simple_root.setAlpha(argNumber);
	};

	simple.styleChanged = function(argString) {
		b_simple_root.styleChanged(argString);
	};

	simple.getVisible = function() {
		return b_simple_root.getVisible();
	};

	simple.setVisible = function(argBoolean) {
		b_simple_root.setVisible(argBoolean);
	};

	simple.getHeight = function() {
		return b_simple_root.getHeight();
	};

	simple.setHeight = function(argNumber) {
		b_simple_root.setHeight(argNumber);
	};

	simple.removeChildAt = function(argInt) {
		return b_simple_root.removeChildAt(argInt);
	};

	simple.getY = function() {
		return b_simple_root.getY();
	};

	simple.setY = function(argNumber) {
		b_simple_root.setY(argNumber);
	};

	simple.getX = function() {
		return b_simple_root.getX();
	};

	simple.setX = function(argNumber) {
		b_simple_root.setX(argNumber);
	};

	simple.getAutomationDelegate = function() {
		return b_simple_root.getAutomationDelegate();
	};

	simple.setAutomationDelegate = function(argObject) {
		b_simple_root.setAutomationDelegate(argObject);
	};

	simple.replayAutomatableEvent = function(argEvent) {
		return b_simple_root.replayAutomatableEvent(argEvent);
	};

	simple.getConstraintValue = function(argString) {
		return b_simple_root.getConstraintValue(argString);
	};

	simple.getMeasuredMinWidth = function() {
		return b_simple_root.getMeasuredMinWidth();
	};

	simple.setMeasuredMinWidth = function(argNumber) {
		b_simple_root.setMeasuredMinWidth(argNumber);
	};

	simple.getToolTip = function() {
		return b_simple_root.getToolTip();
	};

	simple.setToolTip = function(argString) {
		b_simple_root.setToolTip(argString);
	};

	simple.getNumAutomationChildren = function() {
		return b_simple_root.getNumAutomationChildren();
	};

	simple.getParentDocument = function() {
		return b_simple_root.getParentDocument();
	};

	simple.stylesInitialized = function() {
		b_simple_root.stylesInitialized();
	};

	simple.effectFinished = function(argIEffectInstance) {
		b_simple_root.effectFinished(argIEffectInstance);
	};

	simple.getContentMouseY = function() {
		return b_simple_root.getContentMouseY();
	};

	simple.getContentMouseX = function() {
		return b_simple_root.getContentMouseX();
	};

	simple.getExplicitMaxHeight = function() {
		return b_simple_root.getExplicitMaxHeight();
	};

	simple.setExplicitMaxHeight = function(argNumber) {
		b_simple_root.setExplicitMaxHeight(argNumber);
	};

	simple.createAutomationIDPart = function(argIAutomationObject) {
		return b_simple_root.createAutomationIDPart(argIAutomationObject);
	};

	simple.getCurrentState = function() {
		return b_simple_root.getCurrentState();
	};

	simple.setCurrentState = function(argString) {
		b_simple_root.setCurrentState(argString);
	};

	simple.owns = function(argDisplayObject) {
		return b_simple_root.owns(argDisplayObject);
	};

	simple.getShowInAutomationHierarchy = function() {
		return b_simple_root.getShowInAutomationHierarchy();
	};

	simple.setShowInAutomationHierarchy = function(argBoolean) {
		b_simple_root.setShowInAutomationHierarchy(argBoolean);
	};

	simple.drawFocus = function(argBoolean) {
		b_simple_root.drawFocus(argBoolean);
	};

	simple.getFocusEnabled = function() {
		return b_simple_root.getFocusEnabled();
	};

	simple.setFocusEnabled = function(argBoolean) {
		b_simple_root.setFocusEnabled(argBoolean);
	};

	simple.removeEventListener = function(argString, argFunction, argBoolean) {
		b_simple_root.removeEventListener(argString, argFunction, argBoolean);
	};

	simple.createComponentsFromDescriptors = function(argBoolean) {
		b_simple_root.createComponentsFromDescriptors(argBoolean);
	};

	simple.getViewMetricsAndPadding = function() {
		return b_simple_root.getViewMetricsAndPadding();
	};

	simple.getMaxVerticalScrollPosition = function() {
		return b_simple_root.getMaxVerticalScrollPosition();
	};

	simple.getVerticalLineScrollSize = function() {
		return b_simple_root.getVerticalLineScrollSize();
	};

	simple.setVerticalLineScrollSize = function(argNumber) {
		b_simple_root.setVerticalLineScrollSize(argNumber);
	};

	simple.getIcon = function() {
		return b_simple_root.getIcon();
	};

	simple.setIcon = function(argClass) {
		b_simple_root.setIcon(argClass);
	};

	simple.regenerateStyleCache = function(argBoolean) {
		b_simple_root.regenerateStyleCache(argBoolean);
	};

	simple.localToContent = function(argPoint) {
		return b_simple_root.localToContent(argPoint);
	};

	simple.styleChanged = function(argString) {
		b_simple_root.styleChanged(argString);
	};

	simple.notifyStyleChangeInChildren = function(argString, argBoolean) {
		b_simple_root.notifyStyleChangeInChildren(argString, argBoolean);
	};

	simple.getHorizontalScrollPosition = function() {
		return b_simple_root.getHorizontalScrollPosition();
	};

	simple.setHorizontalScrollPosition = function(argNumber) {
		b_simple_root.setHorizontalScrollPosition(argNumber);
	};

	simple.getNumChildren = function() {
		return b_simple_root.getNumChildren();
	};

	simple.getLabel = function() {
		return b_simple_root.getLabel();
	};

	simple.setLabel = function(argString) {
		b_simple_root.setLabel(argString);
	};

	simple.getCreatingContentPane = function() {
		return b_simple_root.getCreatingContentPane();
	};

	simple.setCreatingContentPane = function(argBoolean) {
		b_simple_root.setCreatingContentPane(argBoolean);
	};

	simple.getHorizontalScrollPolicy = function() {
		return b_simple_root.getHorizontalScrollPolicy();
	};

	simple.setHorizontalScrollPolicy = function(argString) {
		b_simple_root.setHorizontalScrollPolicy(argString);
	};

	simple.contains = function(argDisplayObject) {
		return b_simple_root.contains(argDisplayObject);
	};

	simple.getHorizontalPageScrollSize = function() {
		return b_simple_root.getHorizontalPageScrollSize();
	};

	simple.setHorizontalPageScrollSize = function(argNumber) {
		b_simple_root.setHorizontalPageScrollSize(argNumber);
	};

	simple.globalToContent = function(argPoint) {
		return b_simple_root.globalToContent(argPoint);
	};

	simple.getBorderMetrics = function() {
		return b_simple_root.getBorderMetrics();
	};

	simple.removeChild = function(argDisplayObject) {
		return b_simple_root.removeChild(argDisplayObject);
	};

	simple.getAutoLayout = function() {
		return b_simple_root.getAutoLayout();
	};

	simple.setAutoLayout = function(argBoolean) {
		b_simple_root.setAutoLayout(argBoolean);
	};

	simple.addEventListener = function(argString, argFunction, argBoolean1, argInt, argBoolean2) {
		b_simple_root.addEventListener(argString, argFunction, argBoolean1, argInt, argBoolean2);
	};

	simple.setChildIndex = function(argDisplayObject, argInt) {
		b_simple_root.setChildIndex(argDisplayObject, argInt);
	};

	simple.getChildren = function() {
		return b_simple_root.getChildren();
	};

	simple.setDoubleClickEnabled = function(argBoolean) {
		b_simple_root.setDoubleClickEnabled(argBoolean);
	};

	simple.getChildByName = function(argString) {
		return b_simple_root.getChildByName(argString);
	};

	simple.getVerticalScrollPolicy = function() {
		return b_simple_root.getVerticalScrollPolicy();
	};

	simple.setVerticalScrollPolicy = function(argString) {
		b_simple_root.setVerticalScrollPolicy(argString);
	};

	simple.finishPrint = function(argObject, argIFlexDisplayObject) {
		b_simple_root.finishPrint(argObject, argIFlexDisplayObject);
	};

	simple.getVerticalScrollPosition = function() {
		return b_simple_root.getVerticalScrollPosition();
	};

	simple.setVerticalScrollPosition = function(argNumber) {
		b_simple_root.setVerticalScrollPosition(argNumber);
	};

	simple.getCreationPolicy = function() {
		return b_simple_root.getCreationPolicy();
	};

	simple.setCreationPolicy = function(argString) {
		b_simple_root.setCreationPolicy(argString);
	};

	simple.setEnabled = function(argBoolean) {
		b_simple_root.setEnabled(argBoolean);
	};

	simple.getContentMouseY = function() {
		return b_simple_root.getContentMouseY();
	};

	simple.getContentMouseX = function() {
		return b_simple_root.getContentMouseX();
	};

	simple.contentToLocal = function(argPoint) {
		return b_simple_root.contentToLocal(argPoint);
	};

	simple.validateDisplayList = function() {
		b_simple_root.validateDisplayList();
	};

	simple.getVerticalPageScrollSize = function() {
		return b_simple_root.getVerticalPageScrollSize();
	};

	simple.setVerticalPageScrollSize = function(argNumber) {
		b_simple_root.setVerticalPageScrollSize(argNumber);
	};

	simple.Container = function() {
		return b_simple_root.Container();
	};

	simple.getBaselinePosition = function() {
		return b_simple_root.getBaselinePosition();
	};

	simple.getChildDescriptors = function() {
		return b_simple_root.getChildDescriptors();
	};

	simple.getData = function() {
		return b_simple_root.getData();
	};

	simple.setData = function(argObject) {
		b_simple_root.setData(argObject);
	};

	simple.getChildAt = function(argInt) {
		return b_simple_root.getChildAt(argInt);
	};

	simple.removeChildAt = function(argInt) {
		return b_simple_root.removeChildAt(argInt);
	};

	simple.contentToGlobal = function(argPoint) {
		return b_simple_root.contentToGlobal(argPoint);
	};

	simple.getChildIndex = function(argDisplayObject) {
		return b_simple_root.getChildIndex(argDisplayObject);
	};

	simple.initialize = function() {
		b_simple_root.initialize();
	};

	simple.getMaxHorizontalScrollPosition = function() {
		return b_simple_root.getMaxHorizontalScrollPosition();
	};

	simple.getViewMetrics = function() {
		return b_simple_root.getViewMetrics();
	};

	simple.getRawChildren = function() {
		return b_simple_root.getRawChildren();
	};

	simple.executeChildBindings = function(argBoolean) {
		b_simple_root.executeChildBindings(argBoolean);
	};

	simple.getHorizontalLineScrollSize = function() {
		return b_simple_root.getHorizontalLineScrollSize();
	};

	simple.setHorizontalLineScrollSize = function(argNumber) {
		b_simple_root.setHorizontalLineScrollSize(argNumber);
	};

	simple.getClipContent = function() {
		return b_simple_root.getClipContent();
	};

	simple.setClipContent = function(argBoolean) {
		b_simple_root.setClipContent(argBoolean);
	};

	simple.createComponentFromDescriptor = function(argComponentDescriptor, argBoolean) {
		return b_simple_root.createComponentFromDescriptor(argComponentDescriptor, argBoolean);
	};

	simple.getDefaultButton = function() {
		return b_simple_root.getDefaultButton();
	};

	simple.setDefaultButton = function(argIFlexDisplayObject) {
		b_simple_root.setDefaultButton(argIFlexDisplayObject);
	};

	simple.executeBindings = function(argBoolean) {
		b_simple_root.executeBindings(argBoolean);
	};

	simple.getVerticalScrollBar = function() {
		return b_simple_root.getVerticalScrollBar();
	};

	simple.setVerticalScrollBar = function(argScrollBar) {
		b_simple_root.setVerticalScrollBar(argScrollBar);
	};

	simple.addChild = function(argDisplayObject) {
		return b_simple_root.addChild(argDisplayObject);
	};

	simple.getHorizontalScrollBar = function() {
		return b_simple_root.getHorizontalScrollBar();
	};

	simple.setHorizontalScrollBar = function(argScrollBar) {
		b_simple_root.setHorizontalScrollBar(argScrollBar);
	};

	simple.addChildAt = function(argDisplayObject, argInt) {
		return b_simple_root.addChildAt(argDisplayObject, argInt);
	};

	simple.getCreationIndex = function() {
		return b_simple_root.getCreationIndex();
	};

	simple.setCreationIndex = function(argInt) {
		b_simple_root.setCreationIndex(argInt);
	};

	simple.getFocusPane = function() {
		return b_simple_root.getFocusPane();
	};

	simple.setFocusPane = function(argSprite) {
		b_simple_root.setFocusPane(argSprite);
	};

	simple.validateSize = function(argBoolean) {
		b_simple_root.validateSize(argBoolean);
	};

	simple.removeAllChildren = function() {
		b_simple_root.removeAllChildren();
	};

	simple.prepareToPrint = function(argIFlexDisplayObject) {
		return b_simple_root.prepareToPrint(argIFlexDisplayObject);
	};

	simple.FlexSprite = function() {
		return b_simple_root.FlexSprite();
	};

	simple.toString = function() {
		return b_simple_root.toString();
	};

	simple.toString = function() {
		return b_simple_root.toString();
	};

	simple.hasOwnProperty = function(argString) {
		return b_simple_root.hasOwnProperty(argString);
	};

	simple.isPrototypeOf = function(argObject) {
		return b_simple_root.isPrototypeOf(argObject);
	};

	simple.propertyIsEnumerable = function(argString) {
		return b_simple_root.propertyIsEnumerable(argString);
	};

	simple.Object = function() {
		return b_simple_root.Object();
	};

	simple.toLocaleString = function() {
		return b_simple_root.toLocaleString();
	};

	simple.setPropertyIsEnumerable = function(argString, argBoolean) {
		b_simple_root.setPropertyIsEnumerable(argString, argBoolean);
	};

	simple.valueOf = function() {
		return b_simple_root.valueOf();
	};

	simple.getHitArea = function() {
		return b_simple_root.getHitArea();
	};

	simple.setHitArea = function(argSprite) {
		b_simple_root.setHitArea(argSprite);
	};

	simple.getDropTarget = function() {
		return b_simple_root.getDropTarget();
	};

	simple.Sprite = function() {
		return b_simple_root.Sprite();
	};

	simple.getUseHandCursor = function() {
		return b_simple_root.getUseHandCursor();
	};

	simple.setUseHandCursor = function(argBoolean) {
		b_simple_root.setUseHandCursor(argBoolean);
	};

	simple.stopDrag = function() {
		b_simple_root.stopDrag();
	};

	simple.startDrag = function(argBoolean, argRectangle) {
		b_simple_root.startDrag(argBoolean, argRectangle);
	};

	simple.getButtonMode = function() {
		return b_simple_root.getButtonMode();
	};

	simple.setButtonMode = function(argBoolean) {
		b_simple_root.setButtonMode(argBoolean);
	};

	simple.getSoundTransform = function() {
		return b_simple_root.getSoundTransform();
	};

	simple.setSoundTransform = function(argSoundTransform) {
		b_simple_root.setSoundTransform(argSoundTransform);
	};

	simple.getGraphics = function() {
		return b_simple_root.getGraphics();
	};

	simple.getTabIndex = function() {
		return b_simple_root.getTabIndex();
	};

	simple.setTabIndex = function(argInt) {
		b_simple_root.setTabIndex(argInt);
	};

	simple.InteractiveObject = function() {
		return b_simple_root.InteractiveObject();
	};

	simple.getTabEnabled = function() {
		return b_simple_root.getTabEnabled();
	};

	simple.setTabEnabled = function(argBoolean) {
		b_simple_root.setTabEnabled(argBoolean);
	};

	simple.getAccessibilityImplementation = function() {
		return b_simple_root.getAccessibilityImplementation();
	};

	simple.setAccessibilityImplementation = function(argAccessibilityImplementation) {
		b_simple_root.setAccessibilityImplementation(argAccessibilityImplementation);
	};

	simple.getMouseEnabled = function() {
		return b_simple_root.getMouseEnabled();
	};

	simple.setMouseEnabled = function(argBoolean) {
		b_simple_root.setMouseEnabled(argBoolean);
	};

	simple.getContextMenu = function() {
		return b_simple_root.getContextMenu();
	};

	simple.setContextMenu = function(argContextMenu) {
		b_simple_root.setContextMenu(argContextMenu);
	};

	simple.getDoubleClickEnabled = function() {
		return b_simple_root.getDoubleClickEnabled();
	};

	simple.setDoubleClickEnabled = function(argBoolean) {
		b_simple_root.setDoubleClickEnabled(argBoolean);
	};

	simple.getFocusRect = function() {
		return b_simple_root.getFocusRect();
	};

	simple.setFocusRect = function(argObject) {
		b_simple_root.setFocusRect(argObject);
	};

	simple.LayoutContainer = function() {
		return b_simple_root.LayoutContainer();
	};

	simple.getConstraintColumns = function() {
		return b_simple_root.getConstraintColumns();
	};

	simple.setConstraintColumns = function(argArray) {
		b_simple_root.setConstraintColumns(argArray);
	};

	simple.getLayout = function() {
		return b_simple_root.getLayout();
	};

	simple.setLayout = function(argString) {
		b_simple_root.setLayout(argString);
	};

	simple.getConstraintRows = function() {
		return b_simple_root.getConstraintRows();
	};

	simple.setConstraintRows = function(argArray) {
		b_simple_root.setConstraintRows(argArray);
	};

	simple.hitTestPoint = function(argNumber1, argNumber2, argBoolean) {
		return b_simple_root.hitTestPoint(argNumber1, argNumber2, argBoolean);
	};

	simple.getVisible = function() {
		return b_simple_root.getVisible();
	};

	simple.setVisible = function(argBoolean) {
		b_simple_root.setVisible(argBoolean);
	};

	simple.getAccessibilityProperties = function() {
		return b_simple_root.getAccessibilityProperties();
	};

	simple.setAccessibilityProperties = function(argAccessibilityProperties) {
		b_simple_root.setAccessibilityProperties(argAccessibilityProperties);
	};

	simple.getAlpha = function() {
		return b_simple_root.getAlpha();
	};

	simple.setAlpha = function(argNumber) {
		b_simple_root.setAlpha(argNumber);
	};

	simple.getBounds = function(argDisplayObject) {
		return b_simple_root.getBounds(argDisplayObject);
	};

	simple.getMouseY = function() {
		return b_simple_root.getMouseY();
	};

	simple.getMouseX = function() {
		return b_simple_root.getMouseX();
	};

	simple.getFilters = function() {
		return b_simple_root.getFilters();
	};

	simple.setFilters = function(argArray) {
		b_simple_root.setFilters(argArray);
	};

	simple.getParent = function() {
		return b_simple_root.getParent();
	};

	simple.getY = function() {
		return b_simple_root.getY();
	};

	simple.setY = function(argNumber) {
		b_simple_root.setY(argNumber);
	};

	simple.getScale9Grid = function() {
		return b_simple_root.getScale9Grid();
	};

	simple.setScale9Grid = function(argRectangle) {
		b_simple_root.setScale9Grid(argRectangle);
	};

	simple.getX = function() {
		return b_simple_root.getX();
	};

	simple.setX = function(argNumber) {
		b_simple_root.setX(argNumber);
	};

	simple.getOpaqueBackground = function() {
		return b_simple_root.getOpaqueBackground();
	};

	simple.setOpaqueBackground = function(argObject) {
		b_simple_root.setOpaqueBackground(argObject);
	};

	simple.getTransform = function() {
		return b_simple_root.getTransform();
	};

	simple.setTransform = function(argTransform) {
		b_simple_root.setTransform(argTransform);
	};

	simple.hitTestObject = function(argDisplayObject) {
		return b_simple_root.hitTestObject(argDisplayObject);
	};

	simple.getScrollRect = function() {
		return b_simple_root.getScrollRect();
	};

	simple.setScrollRect = function(argRectangle) {
		b_simple_root.setScrollRect(argRectangle);
	};

	simple.getHeight = function() {
		return b_simple_root.getHeight();
	};

	simple.setHeight = function(argNumber) {
		b_simple_root.setHeight(argNumber);
	};

	simple.getRect = function(argDisplayObject) {
		return b_simple_root.getRect(argDisplayObject);
	};

	simple.getWidth = function() {
		return b_simple_root.getWidth();
	};

	simple.setWidth = function(argNumber) {
		b_simple_root.setWidth(argNumber);
	};

	simple.getRotation = function() {
		return b_simple_root.getRotation();
	};

	simple.setRotation = function(argNumber) {
		b_simple_root.setRotation(argNumber);
	};

	simple.getRoot = function() {
		return b_simple_root.getRoot();
	};

	simple.getMask = function() {
		return b_simple_root.getMask();
	};

	simple.setMask = function(argDisplayObject) {
		b_simple_root.setMask(argDisplayObject);
	};

	simple.getLoaderInfo = function() {
		return b_simple_root.getLoaderInfo();
	};

	simple.getStage = function() {
		return b_simple_root.getStage();
	};

	simple.localToGlobal = function(argPoint) {
		return b_simple_root.localToGlobal(argPoint);
	};

	simple.getScaleY = function() {
		return b_simple_root.getScaleY();
	};

	simple.setScaleY = function(argNumber) {
		b_simple_root.setScaleY(argNumber);
	};

	simple.getScaleX = function() {
		return b_simple_root.getScaleX();
	};

	simple.setScaleX = function(argNumber) {
		b_simple_root.setScaleX(argNumber);
	};

	simple.getCacheAsBitmap = function() {
		return b_simple_root.getCacheAsBitmap();
	};

	simple.setCacheAsBitmap = function(argBoolean) {
		b_simple_root.setCacheAsBitmap(argBoolean);
	};

	simple.globalToLocal = function(argPoint) {
		return b_simple_root.globalToLocal(argPoint);
	};

	simple.DisplayObject = function() {
		return b_simple_root.DisplayObject();
	};

	simple.getBlendMode = function() {
		return b_simple_root.getBlendMode();
	};

	simple.setBlendMode = function(argString) {
		b_simple_root.setBlendMode(argString);
	};

	simple.getName = function() {
		return b_simple_root.getName();
	};

	simple.setName = function(argString) {
		b_simple_root.setName(argString);
	};

	simple.willTrigger = function(argString) {
		return b_simple_root.willTrigger(argString);
	};

	simple.toString = function() {
		return b_simple_root.toString();
	};

	simple.removeEventListener = function(argString, argFunction, argBoolean) {
		b_simple_root.removeEventListener(argString, argFunction, argBoolean);
	};

	simple.EventDispatcher = function(argIEventDispatcher) {
		return b_simple_root.EventDispatcher(argIEventDispatcher);
	};

	simple.addEventListener = function(argString, argFunction, argBoolean1, argInt, argBoolean2) {
		b_simple_root.addEventListener(argString, argFunction, argBoolean1, argInt, argBoolean2);
	};

	simple.hasEventListener = function(argString) {
		return b_simple_root.hasEventListener(argString);
	};

	simple.dispatchEvent = function(argEvent) {
		return b_simple_root.dispatchEvent(argEvent);
	};

}
