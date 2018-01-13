package PacketSender;

import java.io.Serializable;


/**
 * Uses for hands all the commands that will use for client requests and server handling
 *
 */
public enum Command implements Serializable
{
	getCatalogProducts,
	updateCatalogProduct,
	getFlowers,
	addUsers,
	getUsers,
	getUsersByUserName,
	getMemberShip,
	getMemberShipKey,
	addCustomers,
	getCustomersKeyByuId,
	addAccounts,
	getAccounts,
	getAccountStatus,
	getAccountbycID,
	getUserByuId,
	updateUserByuId,
	updateCustomerByuId,
	updateAccountsBycId	,
	getProductTypes,
	insertCatalogProduct,
	updateCatalogImage,
	insertFlowersInProduct,
	updateProduct,
	updateFlowersInProduct,
	deleteFlowersInProduct,
	getBranchSales,
	getFlowersInProducts,
	getCatalogImage,
	getBranches,
	updateAccounts,
	updateCustomer,
	updateUser,
	getColors,
	addFlower,
	getDiscountsByBranch,
	getComplains,
	addComplain,
	addComplainRefund,
	updateComplain,
	addReply,
	setProductAsDeleted,
	getAllCatalogProducts,
	addSurvey, 
	addQuestions,
	addQuestionsToServey,
	addSaleCatalogInBranch,
	deleteSaleCatalogInBranch,
	getSurvey,
	getBranchBybrId,
	updateSurvey,
	getUserByNameAndPass,
	getEmployeeByUid,
	getQuestions,
	getSurveyQuestions,
	setUserLoggedInState,
	addAnswerSurvey
	}
