set vb t_vb=
set encoding=UTF-8	"使用utf-8编码
set number
set showcmd
set clipboard=unnamed,unnamedplus	"可以从vim复制到剪贴板中
set mouse=a		"可以在buffer的任何地方使用鼠标
set cursorline		"显示当前行
set hlsearch		"显示高亮搜索
"set incsearch
set history=40		"默认指令记录是20
set ruler		"显示行号和列号
set relativenumber " 显示光标当前行号，其他行显示该行的相对行号
set pastetoggle=F3	"F3快捷键于paste模式与否之间转化，防止自动缩进
"set helplang=cn	"设置为中文帮助文档，需下载并配置之后生效
"
""===============文本格式排版====================
set tabstop=4
set shiftwidth=4	"设置自动对齐的缩进级别
set autoindent		"配合下面一条命令根据不同语言类型进行不同的缩进操作
filetype plugin indent on
"set cindent		"以c语言风格自动缩进
"set smartindent	"自动识别以#开头的注释，不进行换行


"===========================选择solarized的模式========================== 
syntax enable  
syntax on 
"solarzed的深色模式  
"set background=dark 
"solarized的浅色模式 
"set background=light 
"colorscheme solarized        "开启背景颜色模式 
"colorscheme meta5
 
"===========================选择molokai的模式============================ 
let g:rehash256 = 1 
"let g:molokai_original = 1    "相较于上一个模式，个人比较喜欢此种模式 
highlight NonText guibg=#060606 
highlight Folded  guibg=#0A0A0A guifg=#9090D0 
"set t_Co=256 
set background=dark 

map <F3> :NERDTreeMirror<CR>
map <F3> :NERDTreeToggle<CR>

" nerd tree 左侧目录树的宽度
let g:NERDTreeWinSize=40


" vundle插件管理配置
filetype plugin indent on
set rtp+=$VIM/vimfiles/bundle/Vundle.vim/
call vundle#begin('$VIM/vimfiles/bundle/')

Plugin 'VundleVim/Vundle.vim'

Plugin 'scrooloose/nerdtree'
Plugin 'mhinz/vim-startify'
" Plugin 'ryanoasis/vim-devicons'
Plugin 'vim-airline/vim-airline'
Plugin 'vim-airline/vim-airline-themes'
" Plugin 'neoclide/coc.nvim', {'branch': 'release'}
Plugin 'jiangmiao/auto-pairs'
Plugin 'neoclide/coc-git'
Plugin 'neoclide/coc-java'
Plugin 'vim-syntastic/syntastic'
Plugin 'tiagofumo/vim-nerdtree-syntax-highlight' 
Plugin 'posva/vim-vue'
Plugin 'yggdroot/indentline'
Plugin 'raimondi/delimitmate'
Plugin 'othree/html5.vim'
Plugin 'gorodinskiy/vim-coloresque'
Plugin 'gregsexton/matchtag'
"Plugin 'amirh/html-autoclosetag'
"Plugin 'JavaScript-Indent'

call vundle#end()
" 
" 常用的命令
" :PluginList       - 列出所有已配置的插件
" :PluginInstall       - 安装插件,追加 `!` 用以更新或使用 :PluginUpdate
" :PluginSearch foo - 搜索 foo ; 追加 `!` 清除本地缓存
" :PluginClean      - 清除未使用插件,需要确认; 追加 `!` 自动批准移除未使用插件
"
" 查阅 :h vundle 获取更多细节和wiki以及FAQ
"
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" vim-airline插件配置
set laststatus=2  "永远显示状态栏
let g:airline_powerline_fonts = 1  " 支持 powerline 字体
let g:airline#extensions#tabline#enabled = 1 " 显示窗口tab和buffer
let g:airline_theme='term'  " murmur配色不错
if !exists('g:airline_symbols')
	let g:airline_symbols = {}
endif
let g:airline_left_sep = '▶'
let g:airline_left_alt_sep = '❯'
let g:airline_right_sep = '◀'
let g:airline_right_alt_sep = '❮'
let g:airline_symbols.linenr = '¶'
let g:airline_symbols.branch = '⎇'
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 快速编译代码
map <F12> :call CompileRunGcc()<CR>

func! CompileRunGcc()
	exec "w"
	if &filetype == 'c'
		exec '!gcc % -o %<'
		exec '!./%<'
	elseif &filetype == 'cpp'
		exec '!gcc % -o %<'
		exec '!./%<'
	elseif &filetype == 'java'
		exec '!javac %'
		exec '!java %<'
	elseif &filetype == 'sh'
		:!time bash %
	endif
endfunc
