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
	updateCustomer,
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
	addAnswerSurvey,
	getAverageAnswersBySurveyId,
	addConclusion,
	getConclusions,
	updateConclusion, 
	getComplainsForReport,
	getIncomeReport,
	CreateCustomProduct,
	getOrderReport,
	getSatisfactionReport,
	deleteUser, 
	getAccountbycIDandBranch,
	getRefunds,
	getReplies,
	getBranchesIncludeService,
	addMemberShipAccount,
	updateMemberShipAccountByAcNum,
	getMemberShipAccountByAcNum,
	getMemberShipAccount,
	createOrder,
	createProductsInOrder,
	createOrderPayments,
	getAccountByuId,
	createDelivery,
	getOrdersByCIdandBrId, 
	getOrderInProductsDetails,
	getAllProductsInOrder,
	getFlowersInProductInOrder,
	getAllFlowersInOrder,
	deleteMemberShipAccountByacNum,
	getCustomers, 
	getPaymentDetails,
	updateOrder,
	addOrderRefund,
	updateAccountBalance
	}
