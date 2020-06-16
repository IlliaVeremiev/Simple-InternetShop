import { Const } from 'properties.js'

export const en = {
	common: {
		head_url: Const.HeadUrl.en
	},
	header: {
		logo: "Online shop",
		link: {
			catalog: "Catalog",
			cart: "Cart",
			login: "Login",
			registration: "Registration",
			logout: "Logout"
		},
		locale: {
			en: "English",
			ru: "Русский"
		},
		greeting: "Hello {username}"
	},
	authorization: {
		login: {
			failure: {
				exception: {
					badcredentials: "Invalid username or password",
					disabled: "Disabled",
					error: "Invalid username or password"
				}
			},
			form: {
				button: {
					submit: "Login"
				},
				label: {
					password: "Password",
					username: "Email"
				}
			},
			page: {
				header: {
					login: "Login"
				}
			}
		},
		registration: {
			form: {
				label: {
					username: "Email",
					password: "Password",
					invalid_username: "Invalid email",
					invalid_password: "Invalid password"
				},
				button: {
					submit: "Registration"
				}
			},
			page: {
				header: {
					registration: "Registration"
				}
			}
		}
	},
	catalog: {
		content: {
			filter: {
				treeview: {
					title: "Categories",
					all: "All"
				},
				search: {
					button: "Search",
					placeholder: "Enter your search query"
				}
			}
		},
		cart: {
			popup: {
				authorization: {
					header: "You need to be authorized",
					body: "It takes less than a minute 😉",
					close_button: "Close",
					login_button: "Login",
					registration_button: "Registration"
				}
			},
			toast: {
				successfuly_added: "'{productname}' successfuly added to cart",
				failed: "Unable to add '{productname}' to cart. {exception}"
			}
		}
	},
	toasts: {
		header: {
			default: {
				info: 'Notification',
				error: 'Error'
			}
		}
	},
	error: {
		page: {
			404: {
				status: "Page not found",
				description_first_line: "The Page you are looking for doesn't exists or an other error occured.",
				description_second_line: "",
				go_back_button: "Go back,",
				description_third_line: " or head over to",
				go_main_button: Const.HeadUrl.en,
				description_fourth_line: "to choose a new direction."
			}
		}
	},
	cart:{
		product: {
			out_of_stock: 'out of stock'
		},
		totals:{
			subtotal_for_products:"Subtotal for products",
			discounts:"Discounts",
			total_for_products:"Total for products",
			tax:"Tax"
		}
	}
};