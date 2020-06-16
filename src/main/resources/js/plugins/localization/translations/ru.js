import { Const } from 'properties.js'

export const ru = {
	common: {
		head_url: Const.HeadUrl.ru
	},
	header: {
		logo: 'Онлайн магазин',
		link: {
			catalog: "Каталог",
			cart: "Корзина",
			login: "Вход",
			registration: "Регистрация",
			logout: "Выйти"
		},
		locale: {
			en: "English",
			ru: "Русский"
		},
		greeting: "Добрый день, {username}"
	},
	authorization: {
		login: {
			failure: {
				exception: {
					badcredentials: "Неверное имя пользователя или пароль",
					disabled: "Заблокирован",
                    error: "Неверное имя пользователя или пароль"
				}
			},
			form: {
				button: {
					submit: "Войти"
				},
				label: {
					password: "Пароль",
					username: "Email"
				}
			},
			page: {
				header: {
					login: "Авторизация"
				}
			}
		},
		registration: {
			form: {
				label: {
					username: "Email",
					password: "Пароль",
					invalid_username: "Введите корректный email",
					invalid_password: "Пароль должен содержать минимум 3 символа"
				},
				button: {
					submit: "Регистрация"
				}
			},
			page: {
				header: {
					registration: "Регистрация"
				}
			}
		}
	},
	catalog: {
		content: {
			filter: {
				treeview: {
					title: "Категории",
					all: "Все"
				},
				search: {
					button: "Поиск",
					placeholder: "Введите имя товара"
				}
			}
		},
		cart: {
			popup: {
				authorization: {
					header: "Сперва Вы должны авторизоваться",
					body: "Это займет не больше минуты 😉",
					close_button: "Отмена",
					login_button: "Вход",
					registration_button: "Регистрация"
				}
			},
			toast: {
				successfuly_added: "'{productname}' добавлен в корзину",
				failed: "Не удалось добавить '{productname}' в корзину. {exception}"
			}
		}
	},
	toasts: {
		header: {
			default: {
				info: 'Уведомление',
				error: 'Ошибка'
			}
		}
	},
	error: {
		page: {
			404: {
				status: "Страница не найдена",
				description_first_line: "Страница, которую вы ищете, не существует, или произошла другая ошибка.",
				description_second_line: "Можете вернуться",
				go_back_button: "назад",
				description_third_line: "или перейти на",
				go_main_button: "главную",
				description_fourth_line: "."
			}
		}
	},
	cart: {
		product: {
			out_of_stock: 'нет на складе'
		},
		totals: {
			subtotal_for_products: "Всего",
			discounts: "Скидка",
			total_for_products: "Со скидкой",
			tax: "Налог"
		}
	}
};